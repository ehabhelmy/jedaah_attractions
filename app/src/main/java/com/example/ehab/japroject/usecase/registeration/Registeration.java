package com.example.ehab.japroject.usecase.registeration;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.Data;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
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
    private Single<RegisterationResponse> registerationResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<RegisterationResponse> registerationResponseDisposableSingleObserver;

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

        registerationResponseDisposableSingleObserver = new DisposableSingleObserver<RegisterationResponse>() {
            @Override
            public void onSuccess(RegisterationResponse registerationResponse) {
                if (registerationResponse.getSuccess()) {
                    callback.onSuccess(registerationResponse.getData());
                }else {
                    callback.onError(registerationResponse.getMsg().getErrors().getEmail().get(0));
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
