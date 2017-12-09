package com.example.ehab.japroject.usecase.splash;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.User;
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

public class SplashUseCase implements SplashUseCaseInterface {

    private DataRepository dataRepository;
    private Single<User> userSingle;
    private CompositeDisposable compositeDisposable;
    private Disposable disposable;
    private DisposableSingleObserver<User> userDisposableSingleObserver;

    @Inject
    public SplashUseCase(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void checkUserInCache(BaseCallback<User> userBaseCallback) {
        userDisposableSingleObserver = new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(User user) {
                userBaseCallback.onSuccess(user);
            }

            @Override
            public void onError(Throwable e) {
                userBaseCallback.onError();
            }
        };
        if (!compositeDisposable.isDisposed()){
            userSingle = dataRepository.checkUser();
            disposable = userSingle
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(userDisposableSingleObserver);
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
