package com.example.userasef.parentcontrolapp.AuthenticationPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.userasef.parentcontrolapp.AuthenticationPage.signInPage.SignInFragment;
import com.example.userasef.parentcontrolapp.AuthenticationPage.signUpPage.SignUpFragment;
import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        ActivityUtil.pushFragment(SignInFragment.newInstace(), getSupportFragmentManager(), R.id.fragment_container_authentication, false);
    }

    @Override
    public void onBackPressed() {
        ActivityUtil.goToPreviousFragment(getSupportFragmentManager());
    }

    public void pushSignUpFragment(){
        ActivityUtil.pushFragment(SignUpFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_authentication, true);
    }
}
