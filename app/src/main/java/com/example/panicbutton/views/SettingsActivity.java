package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.panicbutton.R;

public class SettingsActivity extends AppCompatActivity {
    private TextView ownerName;
    private Switch panicDropFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ownerName = findViewById(R.id.ownerName);
        ownerName.setText(EmergencySingleton.getInstance().getOwnerName());
        panicDropFlag = findViewById(R.id.dropSwitch);
       if(EmergencySingleton.getInstance().isDropFlag()) {
            panicDropFlag.setChecked(true);
        }
        else{
            panicDropFlag.setChecked(false);
        }
    }


    public void updateOwnerName(View view) {
        EmergencySingleton.getInstance().setOwnerName(ownerName.getText().toString());
    }

    public void updateDropFlag(View view) {
        if(panicDropFlag.isChecked()) {
            EmergencySingleton.getInstance().setDropFlag(true);
        }else{
            EmergencySingleton.getInstance().setDropFlag(false);
        }
    }
}