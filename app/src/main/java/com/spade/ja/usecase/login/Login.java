package com.spade.ja.usecase.login;

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
 * Created by ehab on 12/21/17.
 */

public class Login implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<LoginResponse> loginResonseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<LoginResponse> loginResponseDisposableSingleObserver;

    @Inject
    public Login(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void login(BaseCallback<LoginResponse> callback, String email, String password){
        loginResponseDisposableSingleObserver = new DisposableSingleObserver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    dataRepository.saveLoggedUser(loginResponse.getData().getUser());
                    dataRepository.saveToken(loginResponse.getData().getToken());
                    callback.onSuccess(loginResponse);
                }else {
                    if (loginResponse.getMsg().getErrors() == null) {
                        callback.onError(loginResponse.getMsg().getMsg());
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
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No Data Connection");
            }
        };
        if (!compositeDisposable.isDisposed()){
            loginResonseSingle = dataRepository.login(email, password);
            disposable = loginResonseSingle
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
