package com.example.userasef.parentcontrolapp.data.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyLatLng {
    private double latitude;
    private double longitude;
    private Date date;

    public MyLatLng() {

    }

    public MyLatLng(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MyLatLng(double latitude, double longitude, Date date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDateAndTime(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());
        return dateFormatter.format(date);
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getTime(){
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(this.date);
//
//        String time = cal.get(Calendar.HOUR_OF_DAY)
//                + " : "
//                + cal.get(Calendar.MINUTE);
//        return time;

        SimpleDateFormat formatter = new SimpleDateFormat("hh : mm", Locale.getDefault());
        return formatter.format(date);
    }
}
