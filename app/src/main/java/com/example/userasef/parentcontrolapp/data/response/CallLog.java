package com.example.userasef.parentcontrolapp.data.response;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CallLog {
    private String number;
    private String length;

    public CallLog(String number, String length) {
        this.number = number;
        this.length = length;
    }

    public CallLog() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
