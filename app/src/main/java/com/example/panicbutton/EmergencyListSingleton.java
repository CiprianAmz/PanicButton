package com.example.panicbutton;

import java.util.ArrayList;

public class EmergencyListSingleton {
    private static EmergencyListSingleton singleton;
    ArrayList<String> contactsList = new ArrayList<>();

    private EmergencyListSingleton() {
        //@Ciprian: dummy inputs - has to be updated

        contactsList.add("Test Contact1 - 07777777777");
        contactsList.add("Test Contact2 - 07777777778");
    }

    public static EmergencyListSingleton getInstance() {
        if(singleton == null) {
            singleton = new EmergencyListSingleton();
        }

        return singleton;
    }

    public ArrayList<String> getContacts() {
        return contactsList;
    }

    public void setContacts(ArrayList<String> contactsList) {
        this.contactsList = contactsList;
    }
}
