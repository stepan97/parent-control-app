package com.example.userasef.parentcontrolapp.createNewUserPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CreateNewUserPresenter implements ICreateNewUserContract.Presenter {

    private ICreateNewUserContract.View mView;
    private Context mContext;
    private static IParentService service;

    public CreateNewUserPresenter(ICreateNewUserContract.View view, Context context){
        mView = view;
        mContext = context;
        service = ParentClient.getClient().create(IParentService.class);
    }

    @Override
    public void createNewUser(final ChildUser childUser) {
        mView.setLoaderVisibility(View.VISIBLE);

        service.addChild(childUser).enqueue(new Callback<ResponseModel<ChildUser>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ChildUser>> call,@NonNull Response<ResponseModel<ChildUser>> response) {
                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.newUserCreationSuccess(response.body().getData());

                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ChildUser>> call,@NonNull Throwable t) {
                if(t instanceof ConnectException){
                    mView.showMessage(mContext.getString(R.string.no_internet));
                }

                mView.setLoaderVisibility(View.GONE);

                mView.newUserCreationSuccess(new ChildUser("", "Test name", "0000000000")); // todo: delete this line
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
