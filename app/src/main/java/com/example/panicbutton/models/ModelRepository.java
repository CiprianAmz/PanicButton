package com.example.panicbutton.models;

import java.util.ArrayList;

public class ModelRepository {
    private static ArrayList<ContactModel> contactsList;
    private static LocationModel location;
    private static UserSettingsModel userSettings;

    public ModelRepository() {
        location = new LocationModel(0, 0);
        userSettings = new UserSettingsModel("Your friend", false);
        contactsList = new ArrayList<>();
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
    }

    public void add_contact(ContactModel contact) {
        contactsList.add(contact);
    }

}
