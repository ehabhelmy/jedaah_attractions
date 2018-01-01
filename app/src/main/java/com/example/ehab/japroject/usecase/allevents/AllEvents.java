package com.example.ehab.japroject.usecase.allevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.allevents.AllEventsResponse;
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
 * Created by ehab on 12/16/17.
 */

public class AllEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AllEventsResponse> eventsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AllEventsResponse> eventsResponseDisposableSingleObserver;
    private String eventsURL;
    public boolean freshData = true;

    @Inject
    public AllEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getAllEvents(BaseCallback<AllEventsResponse> callback, boolean fresh){
        eventsResponseDisposableSingleObserver = new DisposableSingleObserver<AllEventsResponse>() {
            @Override
            public void onSuccess(AllEventsResponse eventsResponse) {
                if (fresh) {
                    dataRepository.saveAllEvents(eventsResponse);
                }
                callback.onSuccess(eventsResponse);
                if (freshData) {
                    eventsURL = eventsResponse.getData().getNextPageUrl();
                }
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getAllEvents(false,null);
                    freshData = false;
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            disposable = dataRepository.getAllEvents(fresh,eventsURL)
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
