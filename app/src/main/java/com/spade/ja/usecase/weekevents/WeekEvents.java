package com.spade.ja.usecase.weekevents;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
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

    public void getWeekEvents(BaseCallback<EventsResponse> callback,boolean fresh) {

        disposableSingleObserver = new DisposableSingleObserver<EventsResponse>() {
            @Override
            public void onSuccess(EventsResponse eventsResponse) {
                if (fresh){
                    dataRepository.saveWeekEvents(eventsResponse);
                }
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getWeekEvents(false);
                }
            }
        };

        if (!compositeDisposable.isDisposed()) {
            weekEventsResponseSingle = dataRepository.getWeekEvents(fresh);
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
