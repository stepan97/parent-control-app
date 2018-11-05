package com.example.userasef.parentcontrolapp.callLogPage;

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
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/8/2018.
 */

public class CallLogFragment extends Fragment implements ICallLogContract.View{

    private RecyclerView recycler;
    private CallLogAdapter adapter;

    public static CallLogFragment newInstance(){
        CallLogFragment fragment = new CallLogFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CallLogFragment(){
        // default constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_log_fragment, container, false);

        initView(view);

        adapter.getCallLogs();

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.VISIBLE);
//    }

    private void initView(View view){
        recycler = view.findViewById(R.id.recycler_call_log);
        adapter = new CallLogAdapter(this);
        adapter.setCallLogList(null);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void showCallLog(ArrayList<MyCallLog> list) {
        adapter.setCallLogList(list);
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
