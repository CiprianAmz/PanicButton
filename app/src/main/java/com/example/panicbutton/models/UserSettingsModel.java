package com.example.panicbutton.models;

public class UserSettingsModel {
    private static String userName;
    private static Boolean DropFlag;

    public UserSettingsModel(String userName, Boolean dropFlag) {
        this.userName = userName;
        DropFlag = dropFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getDropFlag() {
        return DropFlag;
    }

    public void setDropFlag(Boolean dropFlag) {
        DropFlag = dropFlag;
    }
}
