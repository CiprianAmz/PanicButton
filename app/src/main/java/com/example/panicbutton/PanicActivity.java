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

        for(String it: EmergencySingleton.getInstance().getContacts()) {
            panicText.append("\n" + it);
        }
    }
}