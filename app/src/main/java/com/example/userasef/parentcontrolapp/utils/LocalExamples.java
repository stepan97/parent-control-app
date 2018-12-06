package com.example.userasef.parentcontrolapp.utils;

import android.util.Log;

import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by userAsef on 10/9/2018.
 */

public class LocalExamples {

    public static ArrayList<MyCallLog> getCallLog(){
        ArrayList<MyCallLog> myCallLog = new ArrayList<>();

        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));
        myCallLog.add(new MyCallLog("Mesrop", "094 13 99 60", "30", "OUTGOING", new Date()));
        myCallLog.add(new MyCallLog("Vacho", "077 49 70 50", "24", "MISSED", new Date()));
        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));
        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));
        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));
        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));
        myCallLog.add(new MyCallLog("Stepan", "098 28 69 77", "20", "INCOMING", new Date()));

        return myCallLog;
    }

    public static ArrayList<String> getUserNames(){
        ArrayList<String> names =  new ArrayList<>();

        for (int i = 1; i < 40; i++) {
            String s = "Name " + i;
            names.add(s);
        }

        return names;
    }

    public static ArrayList<ChildUser> getChildUsers(){
        ArrayList<ChildUser> users =  new ArrayList<>();

        for (int i = 1; i < 40; i++) {
            String name = "Name " + i;
            String id = String.valueOf(i);

            users.add(new ChildUser(id, name, "accessCode"));
        }

        return users;
    }

    public static ArrayList<MySmsLog> getSMSLogs(){
        ArrayList<MySmsLog> smsLog = new ArrayList<>();

        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "Barev"));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "Hazar barin"));
        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "Inch ka?"));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "Ban che du asa..."));
        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "Im mot el tenc. inches anum ba ?"));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "Voch mi ban tenc."));
        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "Parap es ?"));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "Ha.. Gorc ka ?"));
        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "ha miat tex ka etanq ganq"));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "Lav. ba erb enq etum hima ?"));
        smsLog.add(new MySmsLog("Stepan", "098 28 69 77", new Date(), "INCOMING", "Ha durs ari hima khasnem."));
        smsLog.add(new MySmsLog("Sargis", "097 71 45 31", new Date(), "OUTGOING", "lav."));

        return smsLog;
    }

    public static ArrayList<MyLatLng> getUserLocations(){
        ArrayList<MyLatLng> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());

        try {
            list.add(new MyLatLng(40.1786921, 44.5067962, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1814266, 44.508693, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1846791, 44.5118491, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1853662, 44.5124266, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1855759, 44.5144146, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1852287, 44.5165541, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1845634, 44.5175197, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.184708, 44.5184758, formatter.parse("10-15-2018 10:10")));
        }catch (ParseException ex){
            Log.d("TAGO", "Date parse exception. LocalExamples.java");
            ex.printStackTrace();

            list.add(new MyLatLng(40.1786921, 44.5067962, new Date()));
        }

        return list;
    }
}
