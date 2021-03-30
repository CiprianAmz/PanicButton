package com.example.panicbutton.models;

import java.util.ArrayList;

public class Repository {
    private ArrayList<ContactModel> contactsList;

    public Repository(ArrayList<ContactModel> contactsList) {
        this.contactsList = contactsList;
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
