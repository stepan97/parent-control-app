package com.example.userasef.parentcontrolapp.data.payload;

import android.annotation.TargetApi;
import android.os.Build;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class MyForbiddenLocation {
    @Expose
    private String childId;
    @Expose
    private double latitude;
    @Expose
    private double longitude;

    public MyForbiddenLocation() {

    }

    public MyForbiddenLocation(String childId, double latitude, double longitude){
        this.childId = childId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyForbiddenLocation that = (MyForbiddenLocation) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }
}
