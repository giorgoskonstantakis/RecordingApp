package com.gk.recording.application;

import android.app.Application;
import android.content.SharedPreferences;

public class RecordingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       /* SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
        if(!getSharedPreferences("UserInfo", MODE_PRIVATE).getBoolean("name", true)){
            editor.putBoolean("isFirstTimeUserInApp",true);
            editor.apply();
        }*/
    }
}
