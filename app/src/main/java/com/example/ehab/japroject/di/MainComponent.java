package com.example.ehab.japroject.di;

import com.example.ehab.japroject.ui.Home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ehab on 12/1/17.
 */

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    //TODO : inject activities here

    void inject(HomeActivity homeActivity);
}

