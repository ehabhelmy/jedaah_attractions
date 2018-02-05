package com.spade.ja.usecase.venueinner;

import android.accounts.NetworkErrorException;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
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
 * Created by ehab on 1/22/18.
 */

public class VenueInner implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<VenuesInnerResponse> venuesInnerResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<VenuesInnerResponse> venuesInnerResponseDisposableSingleObserver;

    @Inject
    public VenueInner(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getVenueDetails(BaseCallback<VenuesInnerResponse> callback, int id){
        venuesInnerResponseDisposableSingleObserver = new DisposableSingleObserver<VenuesInnerResponse>() {
            @Override
            public void onSuccess(VenuesInnerResponse venuesInnerResponse) {
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
            venuesInnerResponseSingle = dataRepository.getVenueDetails(id);
            disposable = venuesInnerResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(venuesInnerResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
