package com.example.userasef.parentcontrolapp.homePage.homeFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.utils.Constants;
import com.example.userasef.parentcontrolapp.utils.PreferencesUtils;

/**
 * Created by userAsef on 10/8/2018.
 */

public class HomeFragment extends Fragment implements IHomeContract.View{

    private HomePresenter presenter;
    private TextView text_TextView;

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

    private void initView(View view){
        text_TextView = view.findViewById(R.id.homepage_text_tv);

        String mode = PreferencesUtils.getString(getContext(), Constants.APP_MODE_PREFS_TAG, null);
        if(TextUtils.isEmpty(mode) || mode.equals(Constants.APP_MODE_NORMAL)){
            text_TextView.setText(getString(R.string.welcome_text_home_fragment));
        }else if(mode.equals(Constants.APP_MODE_SPECIAL)){
            text_TextView.setText(R.string.welcome_text_special_mode);
        }
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
