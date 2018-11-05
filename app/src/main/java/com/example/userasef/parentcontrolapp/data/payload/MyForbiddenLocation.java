package com.example.userasef.parentcontrolapp.data.payload;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;

public class MyForbiddenLocation {
    private double latitude;
    private double longitude;

    public MyForbiddenLocation() {

    }

    public MyForbiddenLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
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
