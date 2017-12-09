package com.example.ehab.japroject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.ehab.japroject.di.DaggerMainComponent;
import com.example.ehab.japroject.di.MainComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ehab on 12/1/17.
 */

public class JaApplication extends MultiDexApplication {

    private MainComponent mainComponent;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO : initialize leak canary and crashlytics and FireBase Analytics
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
