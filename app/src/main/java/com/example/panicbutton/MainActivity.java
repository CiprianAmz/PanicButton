package com.example.panicbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void lauchPanicActivity(View view) {
        Log.d("MainActivity", "Panic");
    }

    public void launchLocationActivity(View view) {
        Log.d("MainActivity", "Location");
    }

    public void launchAddContactsActivity(View view) {
        Log.d("MainActivity", "AddContacts");
    }

    public void launchSettingsActivity(View view) {
        Log.d("MainActivity", "Settings");
    }
}