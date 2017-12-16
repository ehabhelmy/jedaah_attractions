package com.example.ehab.japroject.usecase.weekevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;

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

public class WeekEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;
    private Single<EventsResponse> weekEventsResponseSingle;
    private DisposableSingleObserver<EventsResponse> disposableSingleObserver;

    @Inject
    public WeekEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getWeekEvents(BaseCallback<EventsResponse> callback) {

        disposableSingleObserver = new DisposableSingleObserver<EventsResponse>() {
            @Override
            public void onSuccess(EventsResponse eventsResponse) {
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
            }
        };

        if (!compositeDisposable.isDisposed()) {
            weekEventsResponseSingle = dataRepository.getWeekEvents();
            disposable = weekEventsResponseSingle
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