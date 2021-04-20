package com.example.panicbutton.controllers;

public class DropCheckService {
    private static final double MIN_AY_VAL = -2.5;
    private static final double DROP_DIF_VALUE = 4;

    private double prevAy;


    public DropCheckService() {
        prevAy = MIN_AY_VAL;
    }

    public boolean checkForDrop(double ayValue) {
        boolean retVal = false;

        if((ayValue - prevAy > DROP_DIF_VALUE) && (prevAy > MIN_AY_VAL)) {
            retVal = true;
        }

        prevAy = ayValue;

        return retVal;
    }
}
