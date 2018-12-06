package com.example.userasef.parentcontrolapp.AuthenticationPage.signInPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.data.response.Parent;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenter implements ISignInContract.Presenter{

    private ISignInContract.View mView;
    private Context mContext;
    private static IParentService service;

    public SignInPresenter(ISignInContract.View view, Context context){
        this.mView = view;
        mContext = context;
        service = ParentClient.getClient().create(IParentService.class);
    }


    @Override
    public void signIn(@NonNull final ParentPayload parent) {
        mView.setLoaderVisibility(View.VISIBLE);

//        mView.signInSuccess(new Parent("1", "Parent Name", "Parent Email", "Parent Access Token"));

        service.login(parent).enqueue(new Callback<ResponseModel<Parent>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<Parent>> call, @NonNull Response<ResponseModel<Parent>> response) {
                mView.setLoaderVisibility(View.GONE);

                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                if(response.body().getStatus().equals("200")){
                    Parent parent = response.body().getData();
                    Log.d("TAGO", "PARENT: " + parent.toString());
                    mView.signInSuccess(parent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<Parent>> call,@NonNull Throwable t) {
                mView.setLoaderVisibility(View.GONE);

                if(t instanceof ConnectException){
                    mView.signInFailure(true);
                    return;
                }

                Log.d("TAGO", "Request FAILED.");
                t.printStackTrace();

                mView.signInFailure(false);
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
