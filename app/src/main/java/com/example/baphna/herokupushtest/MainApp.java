package com.example.baphna.herokupushtest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.interceptors.ParseLogInterceptor;

/**
 * Created by baphna on 11/19/2016.
 */
public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId") // should correspond to APP_ID env variable
                .clientKey("myMasterKey")
                .addNetworkInterceptor(new ParseLogInterceptor())
                .server("http://herokutpushtest.herokuapp.com/parse/").build());

        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
