package com.example.panicbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView  currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.CurrentLocation);
        currentLocation.setText(EmergencySingleton.getInstance().getLocation());
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
        currentLocation.setText(EmergencySingleton.getInstance().getLocation());

    }

    public void launchManageContactsActivity(View view) {
        Intent intent = new Intent(this, ManageContacts.class);
        startActivity(intent);
    }


    public void launchSettingsActivity(View view) {
        Log.d("MainActivity", "Settings");
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

}