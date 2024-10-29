package com.example.backgroundmusicapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class AppController extends Application {
    private static Context mContext;
    public static Activity currentActivity;
    private static MediaPlayer mediaPlayer;

    public static Context getmContext(){
        return mContext;
    }

    public static void setmContext(Context mContext){
        AppController.mContext = mContext;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        setmContext(getApplicationContext());
        mediaPlayer = new MediaPlayer();
        mediaInitializer();
    }

    public static void mediaInitializer() {
        try{
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            mediaPlayer = MediaPlayer.create(getmContext(),R.raw.under_the_tree_short_version);
            mediaPlayer.setAudioAttributes(attributes);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(1f,1f);
        }
        catch (IllegalStateException illegalStateException){
            illegalStateException.printStackTrace();
        }
    }

    public static void playSound(){
        try{
            if(SettingsPreferences.getMusicEnableDisable(mContext) && !mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }
        catch (IllegalStateException illegalStateException){
            illegalStateException.printStackTrace();
            mediaInitializer();
            mediaPlayer.start();
        }
    }

    public static void stopSound(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
}
