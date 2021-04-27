package com.example.panicbutton.controllers;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.example.panicbutton.models.LocationModel;
import com.example.panicbutton.models.ModelRepository;
import com.example.panicbutton.models.SQLManager;
import com.example.panicbutton.models.UserSettingsModel;

public class DatabaseAsyncTask extends AsyncTask<Void, Void, String> {
    ModelRepository modelRepository;
    private SQLManager sqlManager;
    private SharedPreferences sharedPref;
    private static final String PREFERENCES_KEY = "User_Setings";
    Context context;

    public DatabaseAsyncTask(Context context) {
        this.context = context;
        modelRepository = new ModelRepository(context);
        sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        sqlManager = new SQLManager(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        modelRepository.setLocation(new LocationModel(0, 0));
        modelRepository.setUserSettings(new UserSettingsModel(sharedPref.getString("NAME", "Your friend"), sharedPref.getBoolean("DROP", false)));
        modelRepository.setContactsList_without_dbUpdate(sqlManager.getContacts());

        return null;
    }

}
