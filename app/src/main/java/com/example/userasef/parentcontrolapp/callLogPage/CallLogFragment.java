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
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;
import com.example.userasef.parentcontrolapp.homePage.MainActivity;
import com.example.userasef.parentcontrolapp.view.Loader;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/8/2018.
 */

public class CallLogFragment extends Fragment implements ICallLogContract.View{

    private static final String ARG_PARAM_USER_OBJECT = "user_object";
    private ChildUser mChildUser;
    private RecyclerView recycler;
    private CallLogAdapter adapter;
    private Loader loader;

    public static CallLogFragment newInstance(ChildUser childUser){
        CallLogFragment fragment = new CallLogFragment();

        Gson gson = new Gson();
        String userStr = gson.toJson(childUser, ChildUser.class);

        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USER_OBJECT, userStr);
        fragment.setArguments(args);
        return fragment;
    }

    public CallLogFragment(){
        // default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            Gson gson = new Gson();
            mChildUser = gson.fromJson(getArguments().getString(ARG_PARAM_USER_OBJECT), ChildUser.class);
        }

        // update action bar title
        if(getActivity() != null)
            ((MainActivity)getActivity()).updateActionBar(
                    getResources().getString(R.string.call_log),
                    false,
                    true
            );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.call_log_fragment, container, false);

        initView(view);

        adapter.getCallLogs(mChildUser.getId());

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.VISIBLE);
//    }

    private void initView(View view){
        recycler = view.findViewById(R.id.recycler_call_log);
        adapter = new CallLogAdapter(this, getContext());
        adapter.setCallLogList(null);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recycler.setAdapter(adapter);
        loader = view.findViewById(R.id.loader);
    }

    @Override
    public void showCallLog(ArrayList<MyCallLog> list) {
        adapter.setCallLogList(list);
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
