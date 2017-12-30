package com.example.ehab.japroject.usecase.upcomingevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.history.upcoming.HistoryEvents;
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
 * Created by ehab on 12/30/17.
 */

public class UpcomingEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<HistoryEvents> historyEventsSingle;
    private Disposable disposable;
    private DisposableSingleObserver<HistoryEvents> historyEventsDisposableSingleObserver;

    @Inject
    public UpcomingEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getUpcomingEvents(BaseCallback<HistoryEvents> callback) {
        historyEventsDisposableSingleObserver = new DisposableSingleObserver<HistoryEvents>() {
            @Override
            public void onSuccess(HistoryEvents historyEvents) {
                callback.onSuccess(historyEvents);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()) {
            historyEventsSingle = dataRepository.getUpcomingEvents();
            disposable = historyEventsSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(historyEventsDisposableSingleObserver);
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
