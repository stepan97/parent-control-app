package com.example.userasef.parentcontrolapp.createNewUserPage;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.R;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CreateNewUserFragment extends Fragment implements ICreateNewUserContract.View {

    private EditText name_EditText;
    private Button submit_btn;

    private CreateNewUserPresenter presenter;

    public static CreateNewUserFragment newInstance(){
        Bundle args = new Bundle();

        CreateNewUserFragment fragment = new CreateNewUserFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public CreateNewUserFragment(){
        // default constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_new_user_fragment, container, false);

        initView(view);
        initListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.GONE);
    }

    private void initView(View view){
        presenter = new CreateNewUserPresenter(this);

        name_EditText = view.findViewById(R.id.new_user_name_et);
        submit_btn = view.findViewById(R.id.create_new_user_btn);

        name_EditText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.hint_grey), PorterDuff.Mode.SRC_ATOP);
    }

    private void initListeners() {

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_EditText.getText().toString();
                if(TextUtils.isEmpty(name)){
                    name_EditText.setError(getString(R.string.required_field));
                    return;
                }
//                showMessage("New ChildUser created !!! Name: " + name);
                presenter.createNewUser(name);
            }
        });

    }

    @Override
    public void setLoaderVisibility(int visibility) {

    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), "Message: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newUserCreationSuccess(String name) {
        // todo: show view that notifies about user creating and shows activation key
        Toast.makeText(getContext(), "New user created: " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newUserCreatingError(String message) {
        Toast.makeText(getContext(), "Could not create user: " + message, Toast.LENGTH_SHORT).show();
    }
}
