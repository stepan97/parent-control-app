package com.example.userasef.parentcontrolapp.createNewUserPage;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.homePage.MainActivity;
import com.example.userasef.parentcontrolapp.view.Loader;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CreateNewUserFragment extends Fragment implements ICreateNewUserContract.View {

    private EditText name_EditText;
    private Button submit_btn;
    private Loader loader;

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

        // update action bar title
        if(getActivity() != null)
            ((MainActivity)getActivity()).updateActionBar(
                    getResources().getString(R.string.app_name),
                    false,
                    false
            );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.GONE);
        Log.d("TAGO", "onResume AddNewUser Fragment");
    }

    private void initView(View view){
        presenter = new CreateNewUserPresenter(this, getContext());

        name_EditText = view.findViewById(R.id.new_user_name_et);
        submit_btn = view.findViewById(R.id.create_new_user_btn);

        name_EditText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.hint_grey), PorterDuff.Mode.SRC_ATOP);

        loader = view.findViewById(R.id.loader);
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
                ChildUser child = new ChildUser();
                child.setName(name);
                presenter.createNewUser(child);
            }
        });

    }

    @Override
    public void setLoaderVisibility(int visibility) {
        loader.setVisibility(visibility);
    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), "Message: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newUserCreationSuccess(ChildUser childUser) {
        // todo: show view that notifies about user creating and shows activation key
//        Toast.makeText(getContext(), "New user created: " + name, Toast.LENGTH_SHORT).show();

        String message = childUser.getName() + getString(R.string.new_child_added_dialog_msg) + "\n" + childUser.getAccessCode();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setTitle(R.string.new_child_added)
                .setMessage(message);

        builder.setNegativeButton(getString(R.string.dialog_close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
