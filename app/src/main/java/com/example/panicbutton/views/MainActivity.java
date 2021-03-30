package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.MainActivityController;

public class MainActivity extends AppCompatActivity {
    TextView  currentLocation;
    MainActivityController mainActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.CurrentLocation);

        mainActivityController = new MainActivityController();
        currentLocation.setText(mainActivityController.getLocation());

    }

    public void launchPanicActivity(View view) {
        Log.d("MainActivity", "Panic");

        Intent intent = new Intent(this, PanicActivity.class);
        startActivity(intent);
    }

    public void launchLocationActivity(View view) {
        Log.d("MainActivity", "Location");
        Intent intent = new Intent(this,LocationActivity.class);
        startActivity(intent);
        currentLocation.setText(mainActivityController.getLocation());
    }

    public void launchManageContactsActivity(View view) {
        Intent intent = new Intent(this, ManageContactsActivity.class);
        startActivity(intent);
    }


    public void launchSettingsActivity(View view) {
        Log.d("MainActivity", "Settings");
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }


}