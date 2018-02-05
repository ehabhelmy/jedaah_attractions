package com.spade.ja.usecase.nearbyvenues;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;
import com.spade.ja.util.Constants;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 1/18/18.
 */

public class NearByVenues implements Unsubscribable {
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<VenuesResponse> venuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<VenuesResponse> venuesResponseDisposableSingleObserver;

    @Inject
    public NearByVenues(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getNearbyVenues(LatLng latLng, BaseCallback<VenuesResponse> callback, boolean fresh){
        venuesResponseDisposableSingleObserver = new DisposableSingleObserver<VenuesResponse>() {
            @Override
            public void onSuccess(VenuesResponse eventsResponse) {
                if (fresh){
                    dataRepository.saveNearByVenues(eventsResponse);
                }
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getNearByVenues(latLng,false);
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            venuesResponseSingle = dataRepository.getNearByVenues(latLng,fresh);
            disposable = venuesResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(venuesResponseDisposableSingleObserver);
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
