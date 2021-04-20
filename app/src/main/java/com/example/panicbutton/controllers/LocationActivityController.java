package com.example.panicbutton.controllers;

import android.content.Context;

import com.example.panicbutton.models.ModelRepository;

public class LocationActivityController {
    private ModelRepository modelRepository;

    public LocationActivityController(Context context) {
        modelRepository = new ModelRepository(context);
    }

    public void setLocation(double x, double y) {
        modelRepository.getLocation().setxCoordinate(x);
        modelRepository.getLocation().setyCoordinate(y);
    }

    public String getLocation() {
        return String.format("%.3f", modelRepository.getLocation().getxCoordinate()) + " " +
                String.format("%.3f",modelRepository.getLocation().getyCoordinate());
        }
}
