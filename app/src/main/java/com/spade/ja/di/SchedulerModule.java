package com.spade.ja.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 12/1/17.
 * a module to provide all type of Rx schedulers.
 * by using custom annotation
 */

@Module
public class SchedulerModule {

    @Provides
    @RunOn(SchedularType.IO)
    Scheduler provideIO(){
        return Schedulers.io();
    }

    @Provides
    @RunOn(SchedularType.COMPUTATION)
    Scheduler provideComputation(){
        return Schedulers.computation();
    }

    @Provides
    @RunOn(SchedularType.UI)
    Scheduler provideUI(){
        return AndroidSchedulers.mainThread();
    }
}
