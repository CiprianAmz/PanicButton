package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.panicbutton.R;

public class AddContactActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "pannicbutton.extra.MESSAGE";
    private EditText name;
    private EditText phoneNumber;
    private static final int ADD_REQUEST = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.inputName);
        phoneNumber = findViewById(R.id.inputPhone);
    }

    public void sendContact(View view) {
        String newContact = name.getText().toString() + " - " + phoneNumber.getText().toString() ;

        Intent addContactIntent = new Intent();
        addContactIntent.putExtra(EXTRA_MESSAGE, newContact);
        setResult(ADD_REQUEST, addContactIntent);
        finish();
    }
}