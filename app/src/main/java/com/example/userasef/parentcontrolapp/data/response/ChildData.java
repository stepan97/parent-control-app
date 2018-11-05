package com.example.userasef.parentcontrolapp.data.response;

import java.util.ArrayList;

public class ChildData{
    private ArrayList<MyCallLog> callLogs;
    private ArrayList<MySmsLog> smsLogs;
    private ArrayList<MyLatLng> locations;

    public ChildData() {
        callLogs = new ArrayList<>();
        smsLogs = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public ChildData(ArrayList<MyCallLog> callLogs, ArrayList<MySmsLog> smsLogs, ArrayList<MyLatLng> locations) {
        this(); // call empty constructor for initializing arraylists so that they won't be null

        this.callLogs = callLogs;
        this.smsLogs = smsLogs;
        this.locations = locations;
    }

    public ArrayList<MyCallLog> getCallLogs() {
        return callLogs;
    }

    public void setCallLogs(ArrayList<MyCallLog> callLogs) {
        this.callLogs = callLogs;
    }

    public ArrayList<MySmsLog> getSmsLogs() {
        return smsLogs;
    }

    public void setSmsLogs(ArrayList<MySmsLog> smsLogs) {
        this.smsLogs = smsLogs;
    }

    public ArrayList<MyLatLng> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<MyLatLng> locations) {
        this.locations = locations;
    }
}
