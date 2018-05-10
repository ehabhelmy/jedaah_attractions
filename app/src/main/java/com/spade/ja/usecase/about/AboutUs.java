package com.spade.ja.usecase.about;

import android.accounts.NetworkErrorException;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.about.AboutUsResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AboutUs implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AboutUsResponse> aboutUsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<AboutUsResponse> aboutUsResponseDisposableSingleObserver;

    @Inject
    public AboutUs(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void about(BaseCallback<AboutUsResponse> callback){
        aboutUsResponseDisposableSingleObserver = new DisposableSingleObserver<AboutUsResponse>() {
            @Override
            public void onSuccess(AboutUsResponse venuesInnerResponse) {
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
            aboutUsResponseSingle = dataRepository.about();
            disposable = aboutUsResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(aboutUsResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
