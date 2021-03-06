package com.example.panicbutton.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.panicbutton.R;
import com.example.panicbutton.controllers.ContactsController;

import java.util.ArrayList;

public class ManageContactsActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "pannicbutton.extra.MESSAGE";
    ArrayList<String> contactsList = new ArrayList<>();
    Spinner contactsSpinner;
    ContactsController contactsController;

    public static final int TEXT_REQUEST = 1;
    private static final int EDIT_REQUEST = 10;
    private static final int ADD_REQUEST = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contacts);

        contactsController = new ContactsController(this);
        contactsList = contactsController.getContactsList();
        contactsSpinner = (Spinner) findViewById(R.id.Contacts);
        ArrayAdapter<String>  adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, contactsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactsSpinner.setAdapter(adapter);
    }

    public void launchAddContactActivity(View view) {
        Intent intent = new Intent(this, AddContactActivity.class);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == ADD_REQUEST) {
                String newContact = data.getStringExtra(AddContactActivity.EXTRA_MESSAGE);

                contactsList.add(newContact);
            }
            else if(resultCode == EDIT_REQUEST) {
                String newContact = data.getStringExtra(AddContactActivity.EXTRA_MESSAGE);

                int currentContactPosition = contactsSpinner.getSelectedItemPosition();
                contactsList.set(currentContactPosition, newContact);
            }
        }
    }

    public void deleteContact(View view) {
        try{
            int currentContactPosition = contactsSpinner.getSelectedItemPosition();
            contactsList.remove(currentContactPosition);
        }
        catch (Exception e) {
            // the contact list is empty
        }
    }

    public void launchEditContactActivity(View view) {
        int currentContactPosition = contactsSpinner.getSelectedItemPosition();
        String currentContact = contactsList.get(currentContactPosition);
        Intent intent = new Intent(this, EditContactActivity.class);

        intent.putExtra(EXTRA_MESSAGE,currentContact);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onDestroy() {
        contactsController.setContactList(contactsList);
        super.onDestroy();
    }
}