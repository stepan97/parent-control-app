package com.example.userasef.parentcontrolapp.googleMapsPage;

import android.widget.Toast;

import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.util.ArrayList;

public class MapsPresenter implements IMapsContract.Presenter {

    private IMapsContract.View mView;

    public MapsPresenter(IMapsContract.View view){
        this.mView = view;
    }

    @Override
    public void getUserLocationsForToday(String id) {
        // network request
        ArrayList<MyLatLng> list = LocalExamples.getUserLocations();



        mView.showLocations(LocalExamples.getUserLocations());
    }

    @Override
    public void getForbiddenLocationForUser(int id) {
        // network request
        ArrayList<MyLatLng> list = LocalExamples.getUserLocations();

        mView.showLocations(list);
    }

    @Override
    public void addNewForbiddenLocation(MyForbiddenLocation location) {
        mView.showMessage("New forbidden location uploaded !");
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
