package com.spade.ja.usecase.allvenues;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
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
 * Created by Roma on 1/17/2018.
 */

public class AllVenues implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AllVenuesResponse> venuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AllVenuesResponse> venuesResponseDisposableSingleObserver;
    private String venueURL = null;
    public boolean firstLoad = true;

    @Inject
    public AllVenues(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getAllVenues(BaseCallback<AllVenuesResponse> callback, boolean fresh) {
        venuesResponseDisposableSingleObserver = new DisposableSingleObserver<AllVenuesResponse>() {
            @Override
            public void onSuccess(AllVenuesResponse venuesResponse) {
                //TODO : check if the data is valid
                if (fresh) {
                    dataRepository.saveAllVenues(venuesResponse);
                }
                callback.onSuccess(venuesResponse);
                venueURL = venuesResponse.getData().getNextPageUrl();
                firstLoad = false;
            }

            @Override
            public void onError(Throwable e) {
                if (e.getMessage() != null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)) {
                    callback.onError(e.getMessage());
                } else {
                    dataRepository.getAllVenues(false, venueURL);
                }
            }
        };
        if (!compositeDisposable.isDisposed()) {
            if(!(venueURL == null && firstLoad == false)){
                disposable = dataRepository.getAllVenues(fresh,venueURL)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(venuesResponseDisposableSingleObserver);
                compositeDisposable.add(disposable);
            }
            else {
                callback.onSuccess(null);
            }

        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }
    }
}
