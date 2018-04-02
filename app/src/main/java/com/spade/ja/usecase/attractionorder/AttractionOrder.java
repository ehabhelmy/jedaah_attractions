package com.spade.ja.usecase.attractionorder;

import android.accounts.NetworkErrorException;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 3/18/18.
 */

public class AttractionOrder implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AttractionOrderResponse> attractionOrderResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AttractionOrderResponse> attractionOrderResponseDisposableSingleObserver;

    @Inject
    public AttractionOrder(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void attractionOrder(BaseCallback<AttractionOrderResponse> callback, AttractionOrderRequest attractionOrderRequest){
        attractionOrderResponseDisposableSingleObserver = new DisposableSingleObserver<AttractionOrderResponse>() {
            @Override
            public void onSuccess(AttractionOrderResponse venuesInnerResponse) {
                callback.onSuccess(venuesInnerResponse);
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof NetworkErrorException) {
                    callback.onError("No data Connection");
                }else {
                    callback.onError(e.getMessage());
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            attractionOrderResponseSingle = dataRepository.orderAttraction(attractionOrderRequest);
            disposable = attractionOrderResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(attractionOrderResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
