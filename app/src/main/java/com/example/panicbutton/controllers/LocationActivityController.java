package com.example.panicbutton.controllers;

import com.example.panicbutton.models.ModelRepository;

public class LocationActivityController {
    private ModelRepository modelRepository;

    public LocationActivityController() {
        modelRepository = new ModelRepository();
    }

    public void setLocation(double x, double y) {
        modelRepository.getLocation().setxCoordinate(x);
        modelRepository.getLocation().setyCoordinate(y);
    }

    public String getLocation() {
        return String.valueOf(modelRepository.getLocation().getxCoordinate()) + " " +
            String.valueOf(modelRepository.getLocation().getyCoordinate());
        }
}
