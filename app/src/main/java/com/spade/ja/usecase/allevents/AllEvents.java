package com.spade.ja.usecase.allevents;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;
import com.spade.ja.util.Constants;

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
        eventsURL = "http://dev.spade.studio/jada/public/api/v1/en/events?page=1";
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
            if (eventsURL != null) {
                disposable = dataRepository.getAllEvents(fresh, eventsURL)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(eventsResponseDisposableSingleObserver);
                compositeDisposable.add(disposable);
            }else {
                callback.onSuccess(null);
            }
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
