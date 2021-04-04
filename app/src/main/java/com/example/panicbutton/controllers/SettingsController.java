package com.example.panicbutton.controllers;

import com.example.panicbutton.models.ModelRepository;

public class SettingsController {
    private ModelRepository modelRepository;

    public SettingsController() {
        modelRepository = new ModelRepository();
    }

    public void updateName(String name) {
        modelRepository.getUserSettings().setUserName(name);
    }

    public void updateDropFlag(Boolean DropFlag) {
        modelRepository.getUserSettings().setDropFlag(DropFlag);
    }

    public String getName() {
        return modelRepository.getUserSettings().getUserName();
    }

    public Boolean getDropFlag() {
        return modelRepository.getUserSettings().getDropFlag();
    }
}