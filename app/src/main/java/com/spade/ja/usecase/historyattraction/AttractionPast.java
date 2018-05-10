package com.spade.ja.usecase.historyattraction;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.attractionhistory.AttractionOrderHistoryResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AttractionPast implements Unsubscribable{

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AttractionOrderHistoryResponse> historyEventsSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AttractionOrderHistoryResponse> historyEventsDisposableSingleObserver;

    @Inject
    public AttractionPast(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getPastAttractions(BaseCallback<AttractionOrderHistoryResponse> callback) {
        historyEventsDisposableSingleObserver = new DisposableSingleObserver<AttractionOrderHistoryResponse>() {
            @Override
            public void onSuccess(AttractionOrderHistoryResponse historyEvents) {
                callback.onSuccess(historyEvents);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()) {
            historyEventsSingle = dataRepository.getPastAttractions();
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
