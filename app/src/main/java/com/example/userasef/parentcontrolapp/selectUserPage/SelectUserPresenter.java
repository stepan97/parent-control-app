package com.example.userasef.parentcontrolapp.selectUserPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;

import java.net.ConnectException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectUserPresenter implements ISelectUserContract.Presenter {

    private ISelectUserContract.View mView;
    private IParentService service;
    private Context context;

    public SelectUserPresenter(@NonNull ISelectUserContract.View view,@NonNull Context context){
        this.mView = view;
        service = ParentClient.getClient().create(IParentService.class);
        this.context = context;
    }

    @Override
    public void getAllChildUsers() {
        mView.setLoaderVisibility(View.VISIBLE);

        service.getChildren().enqueue(new Callback<ResponseModel<ArrayList<ChildUser>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ArrayList<ChildUser>>> call,@NonNull Response<ResponseModel<ArrayList<ChildUser>>> response) {
                if(response.body() == null){
                    mView.showMessage(context.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.setAdapterChildUserList(response.body().getData());

                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ArrayList<ChildUser>>> call,@NonNull Throwable t) {
                // loader visibility
                if(t instanceof ConnectException)
                    mView.showMessage(context.getString(R.string.no_internet));

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
