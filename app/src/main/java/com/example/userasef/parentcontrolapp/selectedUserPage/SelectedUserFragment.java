package com.example.userasef.parentcontrolapp.selectedUserPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.callLogPage.CallLogFragment;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.googleMapsPage.MapsFragment;
import com.example.userasef.parentcontrolapp.smsLogPage.SMSLogFragment;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;
import com.google.gson.Gson;

/**
 * Created by userAsef on 10/8/2018.
 */

public class SelectedUserFragment extends Fragment implements View.OnClickListener {

    // todo: Change SelectUser and SELECTEDuser fragment.
    // selectUser fragment will consist only from Fragment
    // selectedUser fragment will consist from presenter, and on each button click will get data from server, then goto fragment

    private static final String ARG_PARAM_USER_OBJECT = "selected_user";

    private ChildUser mChildUser;
    private TextView name_TextView;
    private Button location_btn;
    private Button call_log_btn;
    private Button sms_log_btn;

    public static SelectedUserFragment newInstance(ChildUser childUser){
        Bundle args = new Bundle();


//        args.putString(ARG_PARAM_USER_OBJECT, childUser);
        Gson gson = new Gson();
        String tmpUser = gson.toJson(childUser, ChildUser.class);

        args.putString(ARG_PARAM_USER_OBJECT, tmpUser);

        SelectedUserFragment fragment = new SelectedUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SelectedUserFragment(){
        // default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            Gson gson = new Gson();
            mChildUser = gson.fromJson(getArguments().getString(ARG_PARAM_USER_OBJECT), ChildUser.class);
//            mName = getArguments().getString(ARG_PARAM_USER_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_user_fragment, container, false);

        initView(view);
        initListeners();

        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.GONE);
    }

    private void initView(View view){
        location_btn = view.findViewById(R.id.location_btn);
        call_log_btn = view.findViewById(R.id.call_log_btn);
        sms_log_btn = view.findViewById(R.id.sms_log_btn);

        name_TextView = view.findViewById(R.id.selected_name_tv);
        if(name_TextView != null)
            name_TextView.setText(mChildUser.getName());
    }

    private void initListeners(){
        location_btn.setOnClickListener(this);
        call_log_btn.setOnClickListener(this);
        sms_log_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_btn:
                ActivityUtil.pushFragment(MapsFragment.newInstance(mChildUser), getActivity().getSupportFragmentManager(), R.id.fragment_container_main, true);
                break;
            case R.id.call_log_btn:
                ActivityUtil.pushFragment(CallLogFragment.newInstance(), getActivity().getSupportFragmentManager(), R.id.fragment_container_main, true);
                break;
            case R.id.sms_log_btn:
                ActivityUtil.pushFragment(SMSLogFragment.newInstance(), getActivity().getSupportFragmentManager(), R.id.fragment_container_main, true);
                break;
            default:
                return;
        }
    }
}
