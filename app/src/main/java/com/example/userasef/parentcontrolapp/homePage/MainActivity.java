package com.example.userasef.parentcontrolapp.homePage;

import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.createNewUserPage.CreateNewUserFragment;
import com.example.userasef.parentcontrolapp.homePage.homeFragment.HomeFragment;
import com.example.userasef.parentcontrolapp.selectUserPage.SelectUserFragment;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageView backButton_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();

        ActivityUtil.pushFragment(HomeFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
        initBottomNavigationView();
    }

    private void initBottomNavigationView(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if(bottomNavigationView == null){
            bottomNavigationView.setVisibility(View.GONE);
            return;
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        ActivityUtil.popAllBackStack(getSupportFragmentManager());
                        ActivityUtil.pushFragment(HomeFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
                        break;
                    case R.id.action_devices:
                        ActivityUtil.popAllBackStack(getSupportFragmentManager());
                        ActivityUtil.pushFragment(SelectUserFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
                        break;
                    case R.id.action_create_user:
                        ActivityUtil.popAllBackStack(getSupportFragmentManager());
                        ActivityUtil.pushFragment(CreateNewUserFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
                        break;
                    default: return false;
                }

                updateItemCheckedState(item.getItemId());

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setItemIconTintList(null);
    }

    private void initView(){
        backButton_ImageView = findViewById(R.id.gotoPreviousFragment_btn);
    }

    private void initListeners() {
        backButton_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                manager.popBackStack();

                if (manager.getBackStackEntryCount() == 0) {
                    backButton_ImageView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateItemCheckedState(int actionId){
        Menu menu = bottomNavigationView.getMenu();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtil.goToPreviousFragment(getSupportFragmentManager());
    }
}
