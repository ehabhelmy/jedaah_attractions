package com.example.ehab.japroject.usecase.topvenues;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.topvenues.TopVenuesResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

import dagger.multibindings.IntKey;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 1/18/18.
 */

public class TopVenues implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<VenuesResponse> venuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<VenuesResponse> venuesResponseDisposableSingleObserver;

    @Inject
    public TopVenues(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getTopVenues(BaseCallback<TopVenuesResponse> callback, boolean fresh) {
        venuesResponseDisposableSingleObserver = new DisposableSingleObserver<VenuesResponse>() {
            @Override
            public void onSuccess(VenuesResponse venuesResponse) {
                //TODO : check if the data is valid
                if (fresh){
                    dataRepository.saveTopVenues(venuesResponse);
                }
                callback.onSuccess(venuesResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getTopVenues(false);
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            venuesResponseSingle = dataRepository.getTopVenues(fresh);
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
