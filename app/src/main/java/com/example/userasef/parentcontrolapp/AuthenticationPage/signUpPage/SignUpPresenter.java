package com.example.userasef.parentcontrolapp.AuthenticationPage.signUpPage;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;

public class SignUpPresenter implements ISignUpContract.Presenter {

    private ISignUpContract.View mView;
    private IParentService service;

    public SignUpPresenter(ISignUpContract.View view){
        this.mView = view;
        service = ParentClient.getClient().create(IParentService.class);
    }

    @Override
    public void signUp(@NonNull ParentPayload parentPayload) {
        mView.setLoaderVisibility(View.VISIBLE);

        mView.signUpSuccess();

//        service.register(parentPayload).enqueue(new Callback<ResponseModel<Void>>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseModel<Void>> call,@NonNull Response<ResponseModel<Void>> response) {
//                mView.setLoaderVisibility(View.GONE);
//
//                if(response.body().getErrors() != null){
//                    mView.showMessage(response.body().getErrors());
//                    return;
//                }
//
//                if(response.body().getStatus().equals("200")){
//                    mView.signUpSuccess();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseModel<Void>> call,@NonNull Throwable t) {
//                mView.setLoaderVisibility(View.GONE);
//
//                if(t instanceof ConnectException){
//                    mView.signUpFailure(true);
//                    return;
//                }
//
//                t.printStackTrace();
//
//                mView.signUpFailure(false);
//            }
//        });
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
