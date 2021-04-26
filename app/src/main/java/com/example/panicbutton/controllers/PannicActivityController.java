package com.example.panicbutton.controllers;

import android.content.Context;

import com.example.panicbutton.models.ContactModel;
import com.example.panicbutton.models.ModelRepository;

import java.util.ArrayList;

public class PannicActivityController {
    private ModelRepository modelRepository;

    public PannicActivityController(Context context) {
        modelRepository = new ModelRepository(context);
    }

    public String getPanicText() {
        String panicText = "";
        String xCoordinate = String.valueOf(modelRepository.getLocation().getxCoordinate());
        String yCoordinate = String.valueOf(modelRepository.getLocation().getyCoordinate());

        if(xCoordinate.equals("0.0") && yCoordinate.equals("0.0") ) {
            panicText = "\n" + modelRepository.getUserSettings().getUserName()
                    + " is in danger! ";
        }
        else {
            panicText = "\n" + modelRepository.getUserSettings().getUserName()
                    + " is in danger at the location " + xCoordinate + " " + yCoordinate
                    + "\n\nhttps://www.google.com/maps/search/" + yCoordinate + "+" + xCoordinate;
        }

        return panicText;
    }

    public ArrayList<String> getPhoneNumbers() {
        ArrayList<String> phoneNumbers = new ArrayList<>();

        for(ContactModel contact:modelRepository.getContactsList()) {
            phoneNumbers.add(contact.getPhoneNumber());
        }

        return phoneNumbers;
    }
}
