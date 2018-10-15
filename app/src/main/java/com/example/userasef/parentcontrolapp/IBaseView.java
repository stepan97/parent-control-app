package com.example.userasef.parentcontrolapp;

/**
 * Created by userAsef on 10/8/2018.
 */

public interface IBaseView extends ILoaderView {
    void noInternet(String methodName, Object object);
    void showMessage(String msg);
}
