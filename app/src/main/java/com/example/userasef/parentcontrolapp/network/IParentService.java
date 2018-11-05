package com.example.userasef.parentcontrolapp.network;

import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;

public interface IParentService {
    Call<ResponseModel<ArrayList<MyCallLog>>> getCallLogs();

    Call<ResponseModel<ArrayList<MySmsLog>>> getSmsLogs();

    Call<ResponseModel<ArrayList<MyLatLng>>> getLocations();

    Call<ResponseModel<Void>> addNewForbiddenLocation(@Body MyForbiddenLocation location);
}
