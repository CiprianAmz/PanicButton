package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.panicbutton.R;

public class EditContact extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "pannicbutton.extra.MESSAGE";
    private EditText name;
    private EditText phoneNumber;
    private static final int EDIT_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        name = findViewById(R.id.editName);
        phoneNumber = findViewById(R.id.editPhone);

        Intent intent = getIntent();
        String message = intent.getStringExtra(ManageContacts.EXTRA_MESSAGE);
        String[] parts = message.split(" - ");

        name.setText(parts[0]);
        phoneNumber.setText(parts[1]);
    }

    public void editContact(View view) {
        String newContact = name.getText().toString() + " - " + phoneNumber.getText().toString() ;

        Intent addContactIntent = new Intent();
        addContactIntent.putExtra(EXTRA_MESSAGE, newContact);
        setResult(EDIT_REQUEST, addContactIntent);
        finish();
    }
}