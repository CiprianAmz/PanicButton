package com.example.panicbutton.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.panicbutton.controllers.DatabaseAsyncTask;

import java.util.ArrayList;

public class ModelRepository {
    private SQLManager sqlManager;
    private SharedPreferences sharedPref;
    private static final String PREFERENCES_KEY = "User_Setings";


    private static ArrayList<ContactModel> contactsList;
    private static LocationModel location;
    private static UserSettingsModel userSettings;
    private static boolean initialised_flag = false;

    public ModelRepository(Context context) {
        if(!initialised_flag) {
            initialised_flag = true;
            DatabaseAsyncTask databaseAsyncTask = new DatabaseAsyncTask(context);
            databaseAsyncTask.execute();
        }

        sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        sqlManager = new SQLManager(context);
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
        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings = userSettings;

        editor.putString("NAME", userSettings.getUserName());
        editor.putBoolean("DROP", userSettings.getDropFlag());
        editor.apply();
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings.setUserName(userName);

        editor.putString("NAME", userName);
        editor.apply();
    }

    public void setDropFlag(boolean dropFlag) {
        SharedPreferences.Editor editor = sharedPref.edit();
        this.userSettings.setDropFlag(dropFlag);

        editor.putBoolean("DROP", dropFlag);
        editor.apply();
    }

    public ArrayList<ContactModel> getContactsList() {
        return contactsList;
    }

    public void setContactsList(ArrayList<ContactModel> contactsList) {
        this.contactsList = contactsList;

        sqlManager.updateAllItems(contactsList);
    }

    public void setContactsList_without_dbUpdate(ArrayList<ContactModel> contactsList) {
        this.contactsList = contactsList;
    }

    public void add_contact(ContactModel contact) {
        contactsList.add(contact);

        sqlManager.insertEntry(contact.getName(), contact.getPhoneNumber());
    }

    public void delete_contact(ContactModel contact) {
        for(int i = 0; i < contactsList.size(); i++) {
            if(contactsList.get(i).getName().equals(contact.getName())) {
                contactsList.remove(1);
            }
        }

        sqlManager.deleteEntry(contact.getName());
    }
}
