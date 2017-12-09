package com.example.ehab.japroject.usecase.authentication.login;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 12/2/17.
 */

public class LoginUseCase implements LoginUseCaseInterface {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<UserResponse>  userModelSingle;
    private Disposable disposable;
    private DisposableSingleObserver<UserResponse> disposableSingleObserver;

    @Inject
    public LoginUseCase(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void login(String email, String password, BaseCallback<UserResponse> baseCallback) {
        disposableSingleObserver = new DisposableSingleObserver<UserResponse>() {
            @Override
            public void onSuccess(UserResponse userResponse) {
                baseCallback.onSuccess(userResponse);
            }

            @Override
            public void onError(Throwable e) {
                baseCallback.onError();
            }
        };
        if (!compositeDisposable.isDisposed()){
            userModelSingle = dataRepository.login(email,password);
            disposable = userModelSingle
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(disposableSingleObserver);
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
