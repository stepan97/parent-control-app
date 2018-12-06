package com.example.userasef.parentcontrolapp.googleMapsPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.net.ConnectException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsPresenter implements IMapsContract.Presenter {

    private IMapsContract.View mView;
    private Context mContext;
    private static IParentService service;


    public MapsPresenter(IMapsContract.View view, Context context){
        this.mView = view;
        mContext = context;
        service = ParentClient.getClient().create(IParentService.class);
    }

    @Override
    public void getUserLocationsForToday(String id) {
        // loader visibility
        service.getLocations(id).enqueue(new Callback<ResponseModel<ArrayList<MyLatLng>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ArrayList<MyLatLng>>> call,@NonNull Response<ResponseModel<ArrayList<MyLatLng>>> response) {
                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.showLocations(response.body().getData(), false);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ArrayList<MyLatLng>>> call,@NonNull Throwable t) {
                if(t instanceof ConnectException){
                    mView.showMessage(mContext.getString(R.string.no_internet));
                    ArrayList<MyLatLng> list = LocalExamples.getUserLocations();
                    mView.showLocations(LocalExamples.getUserLocations(), false);
                }
            }
        });
    }

    @Override
    public void getForbiddenLocationForUser(String id) {
        mView.setLoaderVisibility(View.VISIBLE);

        service.getChildForbiddenLocations(id).enqueue(new Callback<ResponseModel<ArrayList<MyForbiddenLocation>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ArrayList<MyForbiddenLocation>>> call,@NonNull Response<ResponseModel<ArrayList<MyForbiddenLocation>>> response) {
                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                // todo: make this code more efficient
                ArrayList<MyForbiddenLocation> list1 = response.body().getData();
                ArrayList<MyLatLng> list2 = new ArrayList<>();

                for (int i = 0; i < list1.size(); i++) {
                    MyForbiddenLocation obj = list1.get(i);
                    MyLatLng obj2 = new MyLatLng();
                    obj2.setLatitude(obj.getLatitude());
                    obj2.setLongitude(obj.getLongitude());
                }

                mView.showLocations(list2, true);
                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ArrayList<MyForbiddenLocation>>> call,@NonNull Throwable t) {
                if(t instanceof ConnectException)
                    mView.showMessage(mContext.getString(R.string.no_internet));

                // todo: delete these lines
//                ArrayList<MyLatLng> list = LocalExamples.getUserLocations();
//                mView.showLocations(list, true);
                mView.setLoaderVisibility(View.GONE);
            }
        });
    }

    @Override
    public void addNewForbiddenLocation(MyForbiddenLocation location) {
        mView.setLoaderVisibility(View.VISIBLE);
        service.addNewForbiddenLocation(location).enqueue(new Callback<ResponseModel<Void>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<Void>> call,@NonNull Response<ResponseModel<Void>> response) {
                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.showMessage("New forbidden location added !");
                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<Void>> call,@NonNull Throwable t) {
                if (t instanceof ConnectException)
                    mView.showMessage(mContext.getString(R.string.no_internet));

//                mView.showMessage("New forbidden location added !"); // todo: delete this line

                mView.setLoaderVisibility(View.GONE);
            }
        });
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
