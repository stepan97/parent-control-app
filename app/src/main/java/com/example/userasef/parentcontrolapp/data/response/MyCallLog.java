package com.example.userasef.parentcontrolapp.data.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by userAsef on 10/9/2018.
 */
public class MyCallLog{

    private int id;
    private String name;
    private String number;
    private String duration;
    private String type;
    private Date date;


    public MyCallLog() {
    }

    public MyCallLog(String name, String number, String duration, String type, Date date) {
        setName(name);
        setNumber(number);
        setDuration(duration);
        setType(type);
        setDate(date);
    }

    public int getId(){return id; }

    public void setId(int id){this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate(){return this.date;}

    public void setDate(Date date){this.date = date;}

    @NonNull
    @Override
    public String toString() {
        String s = "[ ";

        s += "name: " + name + ", ";
        s += "number: " + number + ", ";
        s += "duration: " + duration + ", ";
        s += "type: " + type + ", ";
        if(date != null)
            s += "date: " + date.toString();

        return s + "]";
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        MyCallLog call = (MyCallLog) obj;



        return number.equals(call.getNumber()) &&
                duration.equals(call.getDuration()) &&
                type.equals(call.getType()) &&
                date.equals(call.getDate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (number == null ? 0 : number.hashCode());
        result = prime * result + (duration == null ? 0 : duration.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (date == null ? 0 : date.hashCode());
        return result;
    }
}
