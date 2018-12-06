package com.example.userasef.parentcontrolapp.network;

import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;
import com.example.userasef.parentcontrolapp.data.response.Parent;
import com.example.userasef.parentcontrolapp.utils.ApiEndpoints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IParentService {

    @POST(ApiEndpoints.PARENT_LOGIN)
    Call<ResponseModel<Parent>> login(@Body ParentPayload parentPayload);

    // todo: make sure payload does not send name
    @POST(ApiEndpoints.PARENT_REGISTER)
    Call<ResponseModel<Void>> register(@Body ParentPayload payload);

    @POST(ApiEndpoints.GET_CHILD_CALL_LOGS)
    Call<ResponseModel<ArrayList<MyCallLog>>> getCallLogs(@Path("id") String id);

    @POST(ApiEndpoints.GET_CHILD_SMS_LOGS)
    Call<ResponseModel<ArrayList<MySmsLog>>> getSmsLogs(@Path("id") String id);

    @POST(ApiEndpoints.GET_CHILD_LOCATIONS)
    Call<ResponseModel<ArrayList<MyLatLng>>> getLocations(@Path("id") String id);

    @POST(ApiEndpoints.ADD_FORBIDDEN_LOCATION)
    Call<ResponseModel<Void>> addNewForbiddenLocation(@Body MyForbiddenLocation location);

    @POST(ApiEndpoints.ADD_CHILD)
    Call<ResponseModel<ChildUser>> addChild(@Body ChildUser childUser);

    @POST(ApiEndpoints.GET_CHILDREN)
    Call<ResponseModel<ArrayList<ChildUser>>> getChildren();

    @POST(ApiEndpoints.GET_CHILD_FORBIDDEN_LOCATIONS)
    Call<ResponseModel<ArrayList<MyForbiddenLocation>>> getChildForbiddenLocations(@Path("id") String id);
}
