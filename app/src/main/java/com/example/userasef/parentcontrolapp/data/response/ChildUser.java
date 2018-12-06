package com.example.userasef.parentcontrolapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildUser {
    @SerializedName("_id")
    private String id;
    @Expose
    private String name;
    @Expose
    private String accessCode;

    public ChildUser(){}

    public ChildUser(String id, String name, String accessCode){
        this.id = id;
        this.name = name;
        this.accessCode = accessCode;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
