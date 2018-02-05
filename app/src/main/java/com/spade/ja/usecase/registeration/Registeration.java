package com.spade.ja.usecase.registeration;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.login.Data;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

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
                    if (loginResponse.getMsg().getErrors() != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String str : loginResponse.getMsg().getErrors()) {
                            stringBuilder.append(str + ", ");
                        }
                        StringBuilder error = stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
                        callback.onError(error.toString());
                    } else {
                        callback.onError("Server Error");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No Data Connection");
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
