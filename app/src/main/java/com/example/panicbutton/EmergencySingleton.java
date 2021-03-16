package com.example.panicbutton;

import java.util.ArrayList;

public class EmergencySingleton {
    private static EmergencySingleton singleton;
    private ArrayList<String> contactsList = new ArrayList<>();
    private String ownerName;
    private String location;
    private boolean dropFlag;

    private EmergencySingleton() {
        //@Ciprian: dummy inputs - has to be updated

        contactsList.add("Test Contact1 - 07777777777");
        contactsList.add("Test Contact2 - 07777777778");
        dropFlag = false;
        ownerName = "Your friend";
        location = "Location is OFF";
    }


    public static EmergencySingleton getInstance() {
        if(singleton == null) {
            singleton = new EmergencySingleton();
        }

        return singleton;
    }

    public ArrayList<String> getContacts() {
        return contactsList;
    }

    public void setContacts(ArrayList<String> contactsList) {
        this.contactsList = contactsList;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isDropFlag() {
        return dropFlag;
    }

    public void setDropFlag(boolean dropFlag) {
        this.dropFlag = dropFlag;
    }
}
