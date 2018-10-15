package com.example.userasef.parentcontrolapp.smsLogPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userasef.parentcontrolapp.R;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/10/2018.
 */

public class SMSLogFragment extends Fragment implements ISMSLogContract.View {

    private SMSLogAdapter adapter;
    private RecyclerView recycler;

    public static SMSLogFragment newInstance(){
        SMSLogFragment fragment = new SMSLogFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SMSLogFragment(){
        // default constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_log_fragment, container, false);

        initView(view);

        adapter.getSMSLogs();

        return view;
    }

    private void initView(View view){
        recycler = view.findViewById(R.id.sms_log_recycler);
        adapter = new SMSLogAdapter(this);
        adapter.setSMSLogList(null);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void showSMSLog(ArrayList<String> list) {
        adapter.setSMSLogList(list);
    }

    @Override
    public void setLoaderVisibility(int visibility) {

    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {

    }
}
