package com.example.userasef.parentcontrolapp.homePage;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.createNewUserPage.CreateNewUserFragment;
import com.example.userasef.parentcontrolapp.homePage.homeFragment.HomeFragment;
import com.example.userasef.parentcontrolapp.selectUserPage.SelectUserFragment;
import com.example.userasef.parentcontrolapp.settingsPage.SettingsActivity;
import com.example.userasef.parentcontrolapp.utils.ActivityUtil;
import com.example.userasef.parentcontrolapp.utils.PreferencesUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static String LANGUANGE_PREFS_KEY = "app_language";
    private BottomNavigationView bottomNavigationView;
    private ImageView backButton_ImageView;
    private ImageView language_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();

        setAppLanguage("hy");
        PreferencesUtils.putString(MainActivity.this, LANGUANGE_PREFS_KEY, "hy");
        language_ImageView.setImageResource(R.drawable.icon_arm_flag);

        ActivityUtil.pushFragment(HomeFragment.newInstance(), getSupportFragmentManager(), R.id.fragment_container_main, false);
        initBottomNavigationView();
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
        language_ImageView = findViewById(R.id.change_language_btn);

        language_ImageView.setImageResource(R.drawable.icon_us_flag);

        String language = PreferencesUtils.getString(this, LANGUANGE_PREFS_KEY, null);
        if(language == null){
            return;
        }

        setAppLanguage(language);
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

        language_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang = PreferencesUtils.getString(MainActivity.this, LANGUANGE_PREFS_KEY, null);
                if(lang == null){
                    setAppLanguage("hy");
                    PreferencesUtils.putString(MainActivity.this, LANGUANGE_PREFS_KEY, "hy");
                    language_ImageView.setImageResource(R.drawable.icon_arm_flag);
                    return;
                }
                switch (lang) {
                    case "en":
                        setAppLanguage("hy");
                        PreferencesUtils.putString(MainActivity.this, LANGUANGE_PREFS_KEY, "hy");
                        language_ImageView.setImageResource(R.drawable.icon_arm_flag);
                        break;
                    case "hy":
                        setAppLanguage("en");
                        PreferencesUtils.putString(MainActivity.this, LANGUANGE_PREFS_KEY, "en");
                        language_ImageView.setImageResource(R.drawable.icon_us_flag);
                        break;
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

    private void setAppLanguage(String languageToLoad){
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
