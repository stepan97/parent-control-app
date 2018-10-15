package com.example.userasef.parentcontrolapp.data.response;

import java.util.ArrayList;
import java.util.List;

public class ChildUser {
    private String id;
    private String name;
//    private ArrayList<MyLatLng> locations_list;
//    private List<MyLatLng> forbiddenLocations_List;

    public ChildUser(){}

    public ChildUser(String id, String name){
        this.id = id;
        this.name = name;
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
}
