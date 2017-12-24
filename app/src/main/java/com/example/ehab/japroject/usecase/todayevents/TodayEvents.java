package com.example.ehab.japroject.usecase.todayevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class TodayEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<EventsResponse> todayEventsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<EventsResponse> disposableSingleObserver;

    @Inject
    public TodayEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getTodayEvents(BaseCallback<EventsResponse> callback,boolean fresh) {

        disposableSingleObserver = new DisposableSingleObserver<EventsResponse>() {
            @Override
            public void onSuccess(EventsResponse eventsResponse) {
                if (fresh){
                    dataRepository.saveTodayEvents(eventsResponse);
                }
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getTodayEvents(false);
                }
            }
        };

        if (!compositeDisposable.isDisposed()) {
            todayEventsResponseSingle = dataRepository.getTodayEvents(fresh);
            disposable = todayEventsResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }
    }

}
