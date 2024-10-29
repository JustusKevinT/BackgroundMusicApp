package com.example.backgroundmusicapp;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

public class SettingsPreferences {
    public static final String SHOW_MUSIC_ONOFF = "showmusic_enable_disable";
    public static void setMusicEnableDisable(Context context, Boolean result){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putBoolean(SHOW_MUSIC_ONOFF, result);
        preferencesEditor.commit();
    }

    public static boolean getMusicEnableDisable(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(SHOW_MUSIC_ONOFF,Constant.DEFAULT_MUSIC_SETTING);
    }
}
