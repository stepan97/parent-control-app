package com.example.userasef.parentcontrolapp.utils;

import android.util.Log;

import com.example.userasef.parentcontrolapp.data.response.CallLog;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by userAsef on 10/9/2018.
 */

public class LocalExamples {

    public static ArrayList<CallLog> getCallLog(){
        ArrayList<CallLog> callLog = new ArrayList<>();

        callLog.add(new CallLog("090 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("091 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("092 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("093 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("094 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("095 44 33 33", "00 : 05 h"));
        callLog.add(new CallLog("096 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("097 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("098 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("099 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("090 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("010 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("011 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("012 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("013 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("014 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("015 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("016 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("017 33 33 33", "00 : 05 h"));
        callLog.add(new CallLog("018 33 33 33", "00 : 05 h"));

        return callLog;
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

            users.add(new ChildUser(id, name));
        }

        return users;
    }

    public static ArrayList<String> getSMSLogs(){
        ArrayList<String> smsLog = new ArrayList<>();

        smsLog.add("090 33 33 33");
        smsLog.add("091 33 33 33");
        smsLog.add("092 33 33 33");
        smsLog.add("093 33 33 33");
        smsLog.add("094 33 33 33");
        smsLog.add("095 44 33 33");
        smsLog.add("096 33 33 33");
        smsLog.add("097 33 33 33");
        smsLog.add("098 33 33 33");
        smsLog.add("099 33 33 33");
        smsLog.add("090 33 33 33");
        smsLog.add("010 33 33 33");
        smsLog.add("011 33 33 33");
        smsLog.add("012 33 33 33");
        smsLog.add("013 33 33 33");
        smsLog.add("014 33 33 33");
        smsLog.add("015 33 33 33");
        smsLog.add("016 33 33 33");
        smsLog.add("017 33 33 33");
        smsLog.add("018 33 33 33");

        return smsLog;
    }

    public static ArrayList<MyLatLng> getUserLocations(){
        ArrayList<MyLatLng> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());

        try {
            list.add(new MyLatLng(40.1786921, 44.5067962, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1814266, 44.508693, formatter.parse("10-15-2018 10:10")));
            list.add(new MyLatLng(40.1846791, 44.5118491, formatter.parse("10-15-2018 10:10")));
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
