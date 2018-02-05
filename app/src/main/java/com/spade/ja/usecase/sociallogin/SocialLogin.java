package com.spade.ja.usecase.sociallogin;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
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
 * Created by ehab on 12/25/17.
 */

public class SocialLogin implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<LoginResponse> loginResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<LoginResponse> loginResponseDisposableSingleObserver;

    @Inject
    public SocialLogin(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void socialLogin(String facebookId, String googleId, String email, BaseCallback<LoginResponse> callback) {
        loginResponseDisposableSingleObserver = new DisposableSingleObserver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                if (loginResponse.getSuccess()){
                    dataRepository.saveLoggedUser(loginResponse.getData().getUser());
                    dataRepository.saveToken(loginResponse.getData().getToken());
                    callback.onSuccess(loginResponse);
                }else {
                    callback.onError(loginResponse.getMsg().getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No Data Connection");
            }
        };
        if (!compositeDisposable.isDisposed()){
            loginResponseSingle = dataRepository.sociaLogin(facebookId,googleId,email);
            disposable = loginResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(loginResponseDisposableSingleObserver);
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
