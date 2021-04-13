package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.SettingsController;

public class SettingsActivity extends AppCompatActivity {
    private TextView ownerName;
    private Switch panicDropFlag;
    private SettingsController settingsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsController = new SettingsController(this);

        ownerName = findViewById(R.id.ownerName);
        ownerName.setText(settingsController.getName());
        panicDropFlag = findViewById(R.id.dropSwitch);
       if(settingsController.getDropFlag()) {
            panicDropFlag.setChecked(true);
        }
        else{
            panicDropFlag.setChecked(false);
        }
    }


    public void updateOwnerName(View view) {
        settingsController.updateName(ownerName.getText().toString());
    }

    public void updateDropFlag(View view) {
        if(panicDropFlag.isChecked()) {
            settingsController.updateDropFlag(true);
        }else{
            settingsController.updateDropFlag(false);
        }
    }
}