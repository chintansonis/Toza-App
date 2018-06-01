package com.app.toza;

import android.app.Application;

import com.app.toza.api.RestClient;

public class TozaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RestClient.setupRestClient();
    }

}
