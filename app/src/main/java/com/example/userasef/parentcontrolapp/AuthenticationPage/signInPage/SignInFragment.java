package com.example.userasef.parentcontrolapp.AuthenticationPage.signInPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.AuthenticationPage.AuthenticationActivity;
import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.controller.DataController;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.data.response.Parent;
import com.example.userasef.parentcontrolapp.homePage.MainActivity;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;
import com.example.userasef.parentcontrolapp.utils.Constants;
import com.example.userasef.parentcontrolapp.utils.PreferencesUtils;
import com.example.userasef.parentcontrolapp.view.Loader;
import com.google.gson.Gson;

public class SignInFragment extends Fragment implements ISignInContract.View{

    private static IParentService service;
    private SignInPresenter mPresenter;
    private Loader loader;
    private EditText email_EditText;
    private EditText password_EditText;
    private Button signin_btn;
    private TextView forgotPassword_TextView;
    private TextView signup_TextView;

    public static SignInFragment newInstace(){
        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = ParentClient.getClient().create(IParentService.class);
        mPresenter = new SignInPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        initView(view);
        initListeners();

        return view;
    }

    private void initView(View view) {
        email_EditText = view.findViewById(R.id.email_et);
        password_EditText = view.findViewById(R.id.password_et);
        signin_btn = view.findViewById(R.id.signin_btn);
        forgotPassword_TextView = view.findViewById(R.id.forgot_password_tv);
        signup_TextView = view.findViewById(R.id.signup_tv_sign_in_fragment);
        loader = view.findViewById(R.id.loader);
    }

    private void initListeners(){
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loader.setVisibility(View.VISIBLE);

                if(!checkFields()){
                    return;
                }

                ParentPayload parent = new ParentPayload();
                parent.setEmail(email_EditText.getText().toString());
                parent.setPassword(password_EditText.getText().toString());

                Log.d("TAGO", "SENDING REQUEST");
                mPresenter.signIn(parent);
            }
        });

        forgotPassword_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // push forgot Password Page
            }
        });

        signup_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // push signup page
                ((AuthenticationActivity)getActivity()).pushSignUpFragment();
            }
        });
    }

    private boolean checkFields(){
        String email = email_EditText.getText().toString();
        String password = password_EditText.getText().toString();

        boolean email_ok = true;
        boolean password_ok = true;

        if(TextUtils.isEmpty(email)){
            email_EditText.setError(getString(R.string.field_is_required));
            email_ok = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_EditText.setError(getString(R.string.enter_valid_email));
            email_ok = false;
        }else{
            email_EditText.setError(null);
        }

        if(TextUtils.isEmpty(password)){
            password_EditText.setError(getString(R.string.field_is_required));
            password_ok = false;
        }else if(password.length() < 6){
            password_EditText.setError("Password must have at lease 6 characters.");
            password_ok = false;
        }else{
            password_EditText.setError(null);
        }

        return email_ok && password_ok;
    }

    @Override
    public void signInSuccess(Parent parent) {
        DataController.getInstance().setParent(parent);

        Gson gson = new Gson();
        String userStr = gson.toJson(parent, Parent.class);
        PreferencesUtils.putString(getContext(), Constants.USER_GLOBAL, userStr);

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void signInFailure(boolean noInternet) {
        if(noInternet)
            showMessage(getString(R.string.no_internet));
        else
            showMessage(getString(R.string.error_try_again_later));
    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoaderVisibility(int visibility) {
        loader.setVisibility(visibility);
    }
}
