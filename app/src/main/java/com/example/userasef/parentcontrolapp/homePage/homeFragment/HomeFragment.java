package com.example.userasef.parentcontrolapp.homePage.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.selectUserPage.SelectUserFragment;
import com.example.userasef.parentcontrolapp.selectedUserPage.SelectedUserFragment;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;

/**
 * Created by userAsef on 10/8/2018.
 */

public class HomeFragment extends Fragment implements IHomeContract.View{

    private HomePresenter presenter;


    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment(){
        // required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        presenter = new HomePresenter();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.gotoPreviousFragment_btn).setVisibility(View.GONE);
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
