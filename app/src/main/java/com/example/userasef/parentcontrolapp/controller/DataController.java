package com.example.userasef.parentcontrolapp.controller;

import android.content.Context;

import com.example.userasef.parentcontrolapp.data.response.Parent;
import com.example.userasef.parentcontrolapp.utils.Constants;
import com.example.userasef.parentcontrolapp.utils.PreferencesUtils;
import com.google.gson.Gson;

public class DataController {
    private Parent parent;
    private static final DataController ourInstance = new DataController();

    private DataController(){}

    public static DataController getInstance(){
        return ourInstance;
    }

    public Parent getParent(){return parent;}

    public void setParent(Parent parent){
        this.parent = parent;
    }

    public boolean isSignedIn(){
        return parent != null;
    }

    public void init(Context context) {
        String userString = PreferencesUtils.getString(context, Constants.USER_GLOBAL,null);
        Gson gson = new Gson();
        if (userString != null){
            parent = gson.fromJson(userString,Parent.class);
        }
    }
}
