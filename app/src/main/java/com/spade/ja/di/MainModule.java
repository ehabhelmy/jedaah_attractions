package com.spade.ja.di;

import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.local.SharedPref;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ehab on 12/1/17.
 */

@Module
public class MainModule {

    @Provides
    public FusedLocationProviderClient provideFusedLocationClient(){
        return LocationServices.getFusedLocationProviderClient(JaApplication.getContext());
    }
    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    public SharedPref provideSharedPref(){
        return new SharedPref();
    }

    @Provides
    public LoginManager provideFacebookManager(){
        return LoginManager.getInstance();
    }

    @Provides
    public CallbackManager provideFacebookCallBack(){
        return CallbackManager.Factory.create();
    }
}
