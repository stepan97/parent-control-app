package com.example.userasef.parentcontrolapp.SplashPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.userasef.parentcontrolapp.AuthenticationPage.AuthenticationActivity;
import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.controller.DataController;
import com.example.userasef.parentcontrolapp.homePage.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DataController.getInstance().init(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAuthenticationActivity();
            }
        },SPLASH_TIME);
    }

    private void startAuthenticationActivity(){
        if (DataController.getInstance().isSignedIn()){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this, AuthenticationActivity.class));
        }
        finish();
    }
}
