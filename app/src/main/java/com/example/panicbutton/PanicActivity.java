package com.example.panicbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PanicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        TextView panicText = (TextView) findViewById(R.id.PanicText);

        if(!EmergencySingleton.getInstance().getLocation().equals("Location is OFF")) {
            panicText.append("\nThe message: " + EmergencySingleton.getInstance().getOwnerName()
                    + " is in danger at the location " + EmergencySingleton.getInstance().getLocation());
        }
        else {
            panicText.append("\nThe message: " + EmergencySingleton.getInstance().getOwnerName()
                    + " is in danger! ");
        }

        panicText.append("\n\nThe message was sent to: \n");
        for(String it: EmergencySingleton.getInstance().getContacts()) {
            panicText.append("\n" + it);
        }
    }
}