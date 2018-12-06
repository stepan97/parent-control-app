package com.example.userasef.parentcontrolapp.googleMapsPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;

import java.util.ArrayList;

public interface IMapsContract {
    interface View extends IBaseView{
        void showLocations(ArrayList<MyLatLng> list, boolean isForbidden);
    }

    interface Presenter extends IBasePresenter{
        void getUserLocationsForToday(String id);
        void getForbiddenLocationForUser(String id);
        void addNewForbiddenLocation(MyForbiddenLocation location);
    }
}
