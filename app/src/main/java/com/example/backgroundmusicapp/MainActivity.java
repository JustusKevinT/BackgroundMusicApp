package com.example.backgroundmusicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnStartSettingActivity;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        AppController.currentActivity = this;
        if(SettingsPreferences.getMusicEnableDisable(context)){
            try{
                AppController.playSound();
            }
            catch (IllegalStateException illegalStateException){
                illegalStateException.printStackTrace();
            }
        }
        btnStartSettingActivity = findViewById(R.id.button2);
        btnStartSettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause(){
        AppController.stopSound();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        AppController.playSound();
    }
}