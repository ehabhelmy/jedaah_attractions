package com.example.ehab.japroject.usecase.registeration;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.login.Data;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Romisaa on 12/22/2017.
 */

public class Registeration implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<LoginResponse> registerationResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<LoginResponse> registerationResponseDisposableSingleObserver;

    @Inject
    public Registeration(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void register(String userName,
                         String email,
                         String password,
                         String mobile,
                         File image,
                         BaseCallback<Data> callback) {

        registerationResponseDisposableSingleObserver = new DisposableSingleObserver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    dataRepository.saveToken(loginResponse.getData().getToken());
                    dataRepository.saveLoggedUser(loginResponse.getData().getUser());
                    callback.onSuccess(loginResponse.getData());
                }else {
                    callback.onError(loginResponse.getMsg().getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage());
            }
        };

        if (!compositeDisposable.isDisposed()) {
            registerationResponseSingle = dataRepository.register(userName, email, password, mobile, image);
            disposable = registerationResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(registerationResponseDisposableSingleObserver);
            compositeDisposable.add(disposable);
        }

    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }
    }
}
