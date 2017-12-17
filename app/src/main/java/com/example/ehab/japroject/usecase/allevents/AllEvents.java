package com.example.ehab.japroject.usecase.allevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
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
 * Created by ehab on 12/16/17.
 */

public class AllEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<EventsResponse> eventsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<EventsResponse> eventsResponseDisposableSingleObserver;

    @Inject
    public AllEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getAllEvents(BaseCallback<EventsResponse> callback){
        eventsResponseDisposableSingleObserver = new DisposableSingleObserver<EventsResponse>() {
            @Override
            public void onSuccess(EventsResponse eventsResponse) {
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
            }
        };
        if (!compositeDisposable.isDisposed()){
            disposable = dataRepository.getTopEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(eventsResponseDisposableSingleObserver);
            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
