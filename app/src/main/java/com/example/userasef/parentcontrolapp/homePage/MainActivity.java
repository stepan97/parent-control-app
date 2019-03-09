package com.example.userasef.parentcontrolapp.homePage;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.callLogPage.CallLogFragment;
import com.example.userasef.parentcontrolapp.createNewUserPage.CreateNewUserFragment;
import com.example.userasef.parentcontrolapp.homePage.homeFragment.HomeFragment;
import com.example.userasef.parentcontrolapp.selectUserPage.SelectUserFragment;
import com.example.userasef.parentcontrolapp.selectedUserPage.SelectedUserFragment;
import com.example.userasef.parentcontrolapp.settingsPage.SettingsActivity;
import com.example.userasef.parentcontrolapp.smsLogPage.SMSLogFragment;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;

public class MainActivity extends AppCompatActivity {

//    private static String LANGUANGE_PREFS_KEY = "app_language";
    private BottomNavigationView bottomNavigationView;
    private ImageView backButton_ImageView;
//    private ImageView language_ImageView;
    private TextView appname_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();
        initNavigation();

        ActivityUtil.pushFragment(HomeFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
        initBottomNavigationView();
    }

    private void initNavigation(){
        this.getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_container_main);

                if(current instanceof HomeFragment || current instanceof SelectUserFragment || current instanceof CreateNewUserFragment) {
                    updateActionBar(
                            getResources().getString(R.string.app_name),
                            true,
                            false
                    );

                    View v = findViewById(R.id.main_layout);

                    // todo: add check for api version. if(api >= 21) getDrawable(id); else getDrawable(id, getTheme());
                    if(current instanceof HomeFragment)
                        v.setBackground(getResources().getDrawable(R.drawable.home_background));
                    else if(current instanceof SelectUserFragment)
                        v.setBackground(getResources().getDrawable(R.drawable.select_user_background));
                }else {
                    String title = getResources().getString(R.string.app_name);

                    if(current instanceof CallLogFragment) title = getResources().getString(R.string.call_log);

                    if(current instanceof SMSLogFragment) title = getResources().getString(R.string.sms_log);

                    if(current instanceof SelectedUserFragment) title = ((SelectedUserFragment) current).getmChildUser().getName();

                    updateActionBar(
                            title,
                            false,
                            true
                    );
                }
            }
        });
    }

    private void initBottomNavigationView(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if(bottomNavigationView == null){
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
//        language_ImageView = findViewById(R.id.change_language_btn);
        appname_TextView = findViewById(R.id.main_activity_appname);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.options_menu_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void updateActionBar(@NonNull String title, boolean transparent, boolean showBackButton){

        if(appname_TextView == null)
            return;

        appname_TextView.setText(title);


        if(transparent){
            appname_TextView.setBackgroundColor(Color.TRANSPARENT);
            appname_TextView.setTextColor(getResources().getColor(R.color.new_lighter_blue));
        }else{
            appname_TextView.setBackgroundColor(getResources().getColor(R.color.new_darker_blue));
            appname_TextView.setTextColor(getResources().getColor(R.color.white));
        }
        if(showBackButton){
            backButton_ImageView.setVisibility(View.VISIBLE);
        }else{
            backButton_ImageView.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void updateMainBackground(Drawable drawable){
        RelativeLayout layout = findViewById(R.id.main_layout);
        if(layout != null){
            layout.setBackground(drawable);
            if(drawable == null){
                layout.setBackgroundColor(getApplicationContext().getColor(R.color.new_darker_blue));
            }else {
                layout.setBackgroundColor(getApplicationContext().getColor(R.color.new_transparent));
            }
        }
    }
}
