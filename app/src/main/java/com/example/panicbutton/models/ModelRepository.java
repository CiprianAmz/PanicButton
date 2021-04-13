package com.example.panicbutton.models;

import android.content.Context;

import java.util.ArrayList;

public class ModelRepository {
    private SQLManager sqlManager;

    private static ArrayList<ContactModel> contactsList;
    private static LocationModel location;
    private static UserSettingsModel userSettings;
    private static boolean initialised_flag = false;

    public ModelRepository(Context context) {
        if(initialised_flag == false) {
            location = new LocationModel(0, 0);
            userSettings = new UserSettingsModel("Your friend", false);
            initialised_flag = true;
        }

        sqlManager = new SQLManager(context);
        contactsList = sqlManager.getContacts();
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
        this.userSettings = userSettings;
    }

    public ArrayList<ContactModel> getContactsList() {
        return contactsList;
    }

    public void setContactsList(ArrayList<ContactModel> contactsList) {
        this.contactsList = contactsList;

        sqlManager.updateAllItems(contactsList);
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
