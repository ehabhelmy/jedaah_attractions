package com.example.ehab.japroject.usecase.topevents;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.TopEventsResponse;
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
 * Created by ehab on 12/11/17.
 */

public class TopEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<TopEventsResponse> topEventsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<TopEventsResponse> disposableSingleObserver;

    @Inject
    public TopEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getTopEvents(BaseCallback<TopEventsResponse> callback){

        disposableSingleObserver = new DisposableSingleObserver<TopEventsResponse>() {
            @Override
            public void onSuccess(TopEventsResponse topEventsResponse) {
                //TODO : check if the data is valid
                callback.onSuccess(topEventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
            }
        };
        if (!compositeDisposable.isDisposed()){
            topEventsResponseSingle = dataRepository.getTopEvents();
            disposable = topEventsResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
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
