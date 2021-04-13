package com.example.panicbutton.controllers;

import android.content.Context;

import com.example.panicbutton.models.ModelRepository;

public class SettingsController {
    private ModelRepository modelRepository;

    public SettingsController(Context context) {
        modelRepository = new ModelRepository(context);
    }

    public void updateName(String name) {
        modelRepository.setUserName(name);
    }

    public void updateDropFlag(Boolean dropFlag) {
        modelRepository.setDropFlag(dropFlag);
    }

    public String getName() {
        return modelRepository.getUserSettings().getUserName();
    }

    public Boolean getDropFlag() {
        return modelRepository.getUserSettings().getDropFlag();
    }
}
