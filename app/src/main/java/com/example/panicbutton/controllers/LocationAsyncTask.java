package com.example.panicbutton.controllers;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class LocationAsyncTask extends AsyncTask<Void,Void, String> {

    // The TextView where we will show results
    private WeakReference<TextView> mTextView;

    // Constructor that provides a reference to the TextView from the MainActivity
    LocationAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        // TO BE IMPLEMENTED

        return "Not implemented!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
