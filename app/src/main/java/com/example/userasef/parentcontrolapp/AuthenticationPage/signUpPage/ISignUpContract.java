package com.example.userasef.parentcontrolapp.AuthenticationPage.signUpPage;

import android.support.annotation.NonNull;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;

public interface ISignUpContract {
    interface View extends IBaseView {
        void signUpSuccess();
        void signUpFailure(boolean noInternet);
    }

    interface Presenter extends IBasePresenter {
        void signUp(@NonNull ParentPayload parentPayload);
    }
}
