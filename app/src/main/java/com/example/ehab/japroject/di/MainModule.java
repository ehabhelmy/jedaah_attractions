package com.example.ehab.japroject.di;

import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.local.SharedPref;
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
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().create();
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
}
