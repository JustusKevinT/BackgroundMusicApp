package com.example.backgroundmusicapp;

import static com.example.backgroundmusicapp.AppController.stopSound;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch playmusicswith;
    Button btn;
    private boolean isMusicOn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mContext = SettingsActivity.this;
        AppController.currentActivity = this;
        playmusicswith = findViewById(R.id.switch1);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        populateMusic();
    }

    private void switchMusicEnableCheckbox(){
        isMusicOn = !isMusicOn;
        if(isMusicOn){
            SettingsPreferences.setMusicEnableDisable(mContext, true);
            AppController.playSound();
        }
        else{
            SettingsPreferences.setMusicEnableDisable(mContext, false);
            stopSound();
        }
        populateMusic();
    }

    private void populateMusic() {
        if(SettingsPreferences.getMusicEnableDisable(mContext)){
            AppController.playSound();
            playmusicswith.setChecked(true);
        }
        else{
            stopSound();
            playmusicswith.setChecked(false);
        }
        isMusicOn = SettingsPreferences.getMusicEnableDisable(mContext);
    }

    public void viewClickHandler(View view) {
        if (view.getId() == R.id.switch1) {
            switchMusicEnableCheckbox();
        }
        else if (view.getId() == R.id.button){
            finish();
        }
    }
}
