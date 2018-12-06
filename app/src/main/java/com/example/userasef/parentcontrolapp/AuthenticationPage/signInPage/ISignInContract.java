package com.example.userasef.parentcontrolapp.AuthenticationPage.signInPage;

import android.support.annotation.NonNull;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.data.response.Parent;

public interface ISignInContract {
    interface View extends IBaseView{
        void signInSuccess(Parent parent);
        void signInFailure(boolean noInternet);
    }

    interface Presenter extends IBasePresenter{
        void signIn(@NonNull ParentPayload parent);
    }
}
