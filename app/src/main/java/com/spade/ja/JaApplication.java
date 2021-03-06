package com.spade.ja;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.onesignal.OneSignal;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.di.MainComponent;
import com.spade.ja.ui.notification.NotificationHandler;
import com.spade.ja.util.LocaleManager;
import com.telr.mobile.sdk.TelrApplication;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ehab on 12/1/17.
 */

public class JaApplication extends TelrApplication {

    public static Context context;
    private MainComponent mainComponent;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationHandler())
                .init();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        sAnalytics = GoogleAnalytics.getInstance(this);
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    /**
     * Gets the default {@link Tracker} for this {@link JaApplication}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        MultiDex.install(this);
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
