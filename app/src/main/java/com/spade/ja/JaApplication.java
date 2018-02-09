package com.spade.ja;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.di.MainComponent;
import com.facebook.appevents.AppEventsLogger;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ehab on 12/1/17.
 */

public class JaApplication extends MultiDexApplication {

    public static Context context;
    private MainComponent mainComponent;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
//        // TODO : initialize leak canary and crashlytics and FireBase Analytics and facebook sdk
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
        AppEventsLogger.activateApp(this);
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}