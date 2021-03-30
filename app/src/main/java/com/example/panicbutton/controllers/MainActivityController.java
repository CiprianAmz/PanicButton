package com.example.panicbutton.controllers;

import com.example.panicbutton.models.ModelRepository;

public class MainActivityController {
    private ModelRepository modelRepository;

    public String getLocation() {
        modelRepository = new ModelRepository();
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
