package com.example.userasef.parentcontrolapp.AuthenticationPage.signUpPage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.payload.ParentPayload;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;
import com.example.userasef.parentcontrolapp.view.Loader;

public class SignUpFragment extends Fragment implements ISignUpContract.View{

    private Loader loader;
    private SignUpPresenter mPresenter;
    private EditText name_EditText;
    private EditText email_EditText;
    private EditText password_EditText;
    private Button signUp_btn;

    public static SignUpFragment newInstance(){
        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);

        initView(view);
        initListeners();
        mPresenter = new SignUpPresenter(this);

        return view;
    }

    private void initView(View view) {
        name_EditText = view.findViewById(R.id.name_et_signup_fragment);
        email_EditText = view.findViewById(R.id.email_et_signup_fragment);
        password_EditText = view.findViewById(R.id.password_et_signup_fragment);
        signUp_btn = view.findViewById(R.id.signup_btn);
        loader = view.findViewById(R.id.loader);
    }

    private void initListeners() {
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParentPayload parent = new ParentPayload();

                if(checkFields()){
                    parent.setName(name_EditText.getText().toString());
                    parent.setEmail(email_EditText.getText().toString());
                    parent.setPassword(password_EditText.getText().toString());

                    mPresenter.signUp(parent);
                }
            }
        });
    }

    private boolean checkFields(){
        String name = name_EditText.getText().toString();
        String email = email_EditText.getText().toString();
        String password = password_EditText.getText().toString();

        boolean nameOk = true;
        boolean emailOk = true;
        boolean passwordOk = true;

        if(TextUtils.isEmpty(name)){
            nameOk = false;
            name_EditText.setError(getString(R.string.field_is_required));
        }else if(name.length() < 3){
            nameOk = false;
            name_EditText.setError(getString(R.string.must_contain_3_chars));
        }else{
            name_EditText.setError(null);
        }

        if(TextUtils.isEmpty(email)){
            emailOk = false;
            email_EditText.setError(getString(R.string.field_is_required));
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailOk = false;
            email_EditText.setError(getString(R.string.enter_valid_email));
        }else{
            email_EditText.setError(null);
        }

        if(TextUtils.isEmpty(password)){
            passwordOk = false;
            password_EditText.setError(getString(R.string.field_is_required));
        }else if(password.length() < 6){
            passwordOk = false;
            password_EditText.setError(getString(R.string.must_contain_6_chars));
        }else{
            password_EditText.setError(null);
        }

        return nameOk && emailOk && passwordOk;
    }

    @Override
    public void signUpSuccess() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.dialog_message)).setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityUtil.goToPreviousFragment(getFragmentManager());
                    }
                }).show();
    }

    @Override
    public void signUpFailure(boolean noInternet) {
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
