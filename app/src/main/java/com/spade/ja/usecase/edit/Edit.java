package com.spade.ja.usecase.edit;

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
 * Created by ehab on 12/22/2017.
 */

public class Edit implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<LoginResponse> editSingleResponse;
    private Disposable disposable;
    private DisposableSingleObserver<LoginResponse> editDisposable;

    @Inject
    public Edit(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void edit(String userName,
                         String email,
                         String dateOfBirth,
                         String gender,
                         String password,
                         String mobile,
                         File image,
                         BaseCallback<LoginResponse> callback) {

        editDisposable = new DisposableSingleObserver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    dataRepository.saveToken(loginResponse.getData().getToken());
                    dataRepository.saveLoggedUser(loginResponse.getData().getUser());
                    callback.onSuccess(loginResponse);
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
            editSingleResponse = dataRepository.edit(userName, email,dateOfBirth,gender, password, mobile, image);
            disposable = editSingleResponse
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(editDisposable);
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
