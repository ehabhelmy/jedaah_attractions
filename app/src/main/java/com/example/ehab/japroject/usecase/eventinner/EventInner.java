package com.example.ehab.japroject.usecase.eventinner;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
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
 * Created by ehab on 12/20/17.
 */

public class EventInner implements Unsubscribable{

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<EventInnerResponse> eventInnerResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<EventInnerResponse> eventInnerResponseDisposableSingleObserver;

    @Inject
    public EventInner(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getEventDetails(BaseCallback<EventInnerResponse> callback,int id){
        eventInnerResponseDisposableSingleObserver = new DisposableSingleObserver<EventInnerResponse>() {
            @Override
            public void onSuccess(EventInnerResponse eventInnerResponse) {
                callback.onSuccess(eventInnerResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()){
            eventInnerResponseSingle = dataRepository.getEventDetails(id);
            disposable = eventInnerResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(eventInnerResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
