package com.example.panicbutton.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.panicbutton.controllers.DatabaseAsyncTask;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class ModelRepository {
    private SQLManager sqlManager;
    private SharedPreferences sharedPref;
    private static final String PREFERENCES_KEY = "User_Setings";


    private static ArrayList<ContactModel> contactsList;
    private static LocationModel location;
    private static UserSettingsModel userSettings;
    private static boolean initialised_flag = false;
    volatile static boolean busyFlag = false;

    public ModelRepository(Context context) {
        waitFreeTime();

        if(!initialised_flag) {
            initialised_flag = true;
            DatabaseAsyncTask databaseAsyncTask = new DatabaseAsyncTask(context);
            databaseAsyncTask.execute();
        }

        busyFlag = true;

        sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        sqlManager = new SQLManager(context);

        busyFlag = false;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public UserSettingsModel getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettingsModel userSettings) {
        waitFreeTime();
        busyFlag = true;

        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings = userSettings;

        editor.putString("NAME", userSettings.getUserName());
        editor.putBoolean("DROP", userSettings.getDropFlag());
        editor.apply();

        busyFlag = false;
    }

    public void setUserName(String userName) {
        waitFreeTime();
        busyFlag = true;

        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings.setUserName(userName);

        editor.putString("NAME", userName);
        editor.apply();

        busyFlag = false;
    }

    public void setDropFlag(boolean dropFlag) {
        waitFreeTime();
        busyFlag = true;

        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings.setDropFlag(dropFlag);

        editor.putBoolean("DROP", dropFlag);
        editor.apply();

        busyFlag = false;
    }

    public ArrayList<ContactModel> getContactsList() {
        return contactsList;
    }

    public void setContactsList(ArrayList<ContactModel> contactsList) {
        waitFreeTime();
        busyFlag = true;

        this.contactsList = contactsList;

        sqlManager.updateAllItems(contactsList);

        busyFlag = false;
    }

    public void setContactsList_without_dbUpdate(ArrayList<ContactModel> contactsList) {
        this.contactsList = contactsList;
    }

    public void add_contact(ContactModel contact) {
        waitFreeTime();
        busyFlag = true;

        contactsList.add(contact);

        sqlManager.insertEntry(contact.getName(), contact.getPhoneNumber());
        busyFlag = false;
    }

    public void delete_contact(ContactModel contact) {
        waitFreeTime();
        busyFlag = true;

        for(int i = 0; i < contactsList.size(); i++) {
            if(contactsList.get(i).getName().equals(contact.getName())) {
                contactsList.remove(1);
            }
        }

        sqlManager.deleteEntry(contact.getName());
        busyFlag = false;
    }

    public static boolean getBusyFlag() { return busyFlag; }

    private void waitFreeTime() {
        if(busyFlag) {
            while (busyFlag) { }
        }
    }
}
