package com.example.panicbutton.controllers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.lang.ref.WeakReference;

public class LocationAsyncTask extends AsyncTask<Void, Void, String> {

    // The TextView where we will show results
    private WeakReference<TextView> mTextView;
    LocationActivityController locationActivityController;
    public LocationManager locationManager;
    public LocationListener listener;
    public Context context1;

    // Constructor that provides a reference to the TextView from the MainActivity
    @RequiresApi(api = Build.VERSION_CODES.M)
    public LocationAsyncTask( Context context , TextView txt) {
        mTextView = new WeakReference<>(txt);
        context1 = context;
        locationActivityController = new LocationActivityController(context);
       // locationManager = new LocationManager();
        locationManager = (LocationManager) context1.getSystemService(Context.LOCATION_SERVICE);
        Context finalContext = context;
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationActivityController.setLocation(location.getLongitude(), location.getLatitude());
                mTextView.get().setText(locationActivityController.getLocation());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                finalContext.startActivity(i);
            }
        };
    }

    @Override
    protected String doInBackground(Void... voids) {
        while (true) {

            return "";
        }
    }

    protected void onPostExecute(String result) {
        if (ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                }
                return;
            }
        }
        locationManager.requestLocationUpdates("gps", 1, 0, listener);

    }

}
