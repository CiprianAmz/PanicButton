package com.example.panicbutton.controllers;

import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class LocationAsyncTask extends AsyncTask<Void,Void, String> {

    // The TextView where we will show results
    private WeakReference<Location> mTextView;
    LocationActivityController locationActivityController;
    // Constructor that provides a reference to the TextView from the MainActivity
    public LocationAsyncTask(Location location) {
        mTextView = new WeakReference<>(location);
        locationActivityController = new LocationActivityController();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return locationActivityController.getLocation();

    }

    protected void onPostExecute(String result) {
        locationActivityController.setLocation(mTextView.get().getLongitude(), mTextView.get().getLatitude() );

    }

}
