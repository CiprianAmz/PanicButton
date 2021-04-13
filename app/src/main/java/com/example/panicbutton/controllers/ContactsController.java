package com.example.panicbutton.controllers;

import android.content.Context;

import com.example.panicbutton.models.ContactModel;
import com.example.panicbutton.models.ModelRepository;

import java.util.ArrayList;

public class ContactsController {
    private ModelRepository modelRepository;

    public ContactsController(Context context) {
        modelRepository = new ModelRepository(context);
    }

    public void addNewContact(String contact) {
        String[] parts = contact.split(" - ");
        modelRepository.add_contact(new ContactModel(parts[0], parts[1]));
    }

    public void editContact(int position, String contact) {
        String[] parts = contact.split(" - ");
        modelRepository.getContactsList().set(position, new ContactModel(parts[0], parts[1]));
    }

    public ArrayList<String> getContactsList() {
        ArrayList<String> resultContacts = new ArrayList<>();

        for(ContactModel contact:modelRepository.getContactsList()) {
            resultContacts.add(contact.toString());
        }

        return resultContacts;
    }

    public void setContactList(ArrayList<String> contacts) {
        modelRepository.setContactsList(new ArrayList<ContactModel>());

        for(String contact:contacts) {
            this.addNewContact(contact);
        }
    }

}
