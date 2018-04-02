package com.spade.ja;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.appevents.AppEventsLogger;
import com.onesignal.OneSignal;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.di.MainComponent;
import com.spade.ja.ui.notification.NotificationHandler;
import com.spade.ja.util.LocaleManager;

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
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationHandler())
                .init();
        AppEventsLogger.activateApp(this);
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
