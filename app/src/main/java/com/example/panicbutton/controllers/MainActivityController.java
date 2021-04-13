package com.example.panicbutton.controllers;

import android.content.Context;

import com.example.panicbutton.models.ModelRepository;

public class MainActivityController {
    private ModelRepository modelRepository;

    public MainActivityController(Context context) {
        modelRepository = new ModelRepository(context);
    }

    public String getLocation() {
        double xCoordinate = modelRepository.getLocation().getxCoordinate();
        double yCoordinate = modelRepository.getLocation().getyCoordinate();

        if(xCoordinate == 0 && yCoordinate == 0) {
            return "The location is OFF!";
        }
        else {
            return String.valueOf(xCoordinate) + " " + String.valueOf(yCoordinate);
        }
    }
}
