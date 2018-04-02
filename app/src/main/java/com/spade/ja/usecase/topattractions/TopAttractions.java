package com.spade.ja.usecase.topattractions;

import com.spade.ja.datalayer.DataRepository;
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
 * Created by ehab on 3/1/18.
 */

public class TopAttractions implements Unsubscribable {
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<VenuesResponse> venuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<VenuesResponse> venuesResponseDisposableSingleObserver;

    @Inject
    public TopAttractions(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getTopAttractions(BaseCallback<VenuesResponse> callback, boolean fresh) {
        venuesResponseDisposableSingleObserver = new DisposableSingleObserver<VenuesResponse>() {
            @Override
            public void onSuccess(VenuesResponse venuesResponse) {
                //TODO : check if the data is valid
                if (fresh){
                    dataRepository.saveTopAttractions(venuesResponse);
                }
                callback.onSuccess(venuesResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getTopAttractions(false);
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            venuesResponseSingle = dataRepository.getTopAttractions(fresh);
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
