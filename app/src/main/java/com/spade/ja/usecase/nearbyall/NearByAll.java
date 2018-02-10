package com.spade.ja.usecase.nearbyall;

import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
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
 * Created by ehab on 2/10/18.
 */

public class NearByAll implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AllNearByResponse> venuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AllNearByResponse> venuesResponseDisposableSingleObserver;

    @Inject
    public NearByAll(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getNearByAll(LatLng latLng, BaseCallback<AllNearByResponse> callback, boolean fresh){
        venuesResponseDisposableSingleObserver = new DisposableSingleObserver<AllNearByResponse>() {
            @Override
            public void onSuccess(AllNearByResponse eventsResponse) {
                if (fresh){
                    dataRepository.saveNearByAll(eventsResponse);
                }
                callback.onSuccess(eventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getNearByAll(latLng,false);
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            venuesResponseSingle = dataRepository.getNearByAll(latLng,fresh);
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
