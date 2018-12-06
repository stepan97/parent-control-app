package com.example.userasef.parentcontrolapp.settingsPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.utils.Constants;
import com.example.userasef.parentcontrolapp.utils.PreferencesUtils;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner appLanguage_Spinner;
    private SwitchCompat appMode_SwitchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
        initSpinner();
        initListeners();
    }

    private void initView(){
        appLanguage_Spinner = findViewById(R.id.change_language_spinner);
        appMode_SwitchCompat = findViewById(R.id.app_mode_switch_compat);
    }

    private void initListeners(){
        appLanguage_Spinner.setOnItemSelectedListener(this);
        appMode_SwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    PreferencesUtils.putString(getApplicationContext(), Constants.APP_MODE_PREFS_TAG,Constants.APP_MODE_NORMAL);
                }else {
                    PreferencesUtils.putString(getApplicationContext(), Constants.APP_MODE_PREFS_TAG,Constants.APP_MODE_SPECIAL);
                }
            }
        });
    }

    private void initSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        appLanguage_Spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);

        switch (item){
            case "English":
                break;
            case "Հայերեն":
                break;
            case "Русский":
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
