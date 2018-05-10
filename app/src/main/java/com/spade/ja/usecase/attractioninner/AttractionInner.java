package com.spade.ja.usecase.attractioninner;

import android.accounts.NetworkErrorException;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
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
 * Created by ehab on 3/9/18.
 */

public class AttractionInner implements Unsubscribable {
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AttractionInnerResponse> attractionInnerResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AttractionInnerResponse> attractionInnerResponseDisposableSingleObserver;

    @Inject
    public AttractionInner(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getAttractionDetails(BaseCallback<AttractionInnerResponse> callback, int id){
        attractionInnerResponseDisposableSingleObserver = new DisposableSingleObserver<AttractionInnerResponse>() {
            @Override
            public void onSuccess(AttractionInnerResponse venuesInnerResponse) {
                callback.onSuccess(venuesInnerResponse);
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof NetworkErrorException) {
                    callback.onError(JaApplication.context.getString(R.string.connection));
                }else {
                    callback.onError(e.getMessage());
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            attractionInnerResponseSingle = dataRepository.getAttractionsDetails(id);
            disposable = attractionInnerResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(attractionInnerResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
