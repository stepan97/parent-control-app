package com.example.userasef.parentcontrolapp.googleMapsPage;

import com.example.userasef.parentcontrolapp.utils.LocalExamples;

public class MapsPresenter implements IMapsContract.Presenter {

    private IMapsContract.View mView;

    public MapsPresenter(IMapsContract.View view){
        this.mView = view;
    }

    @Override
    public void getUserLocationsForToday(String id) {
        // network request
        mView.showUserLocations(LocalExamples.getUserLocations());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
