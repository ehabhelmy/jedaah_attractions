package com.spade.ja.usecase.LoggedUser;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.login.User;
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
 * Created by ehab on 1/7/18.
 */

public class LoggedUser implements Unsubscribable {
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<User> userSingle;
    private Disposable disposable;
    private DisposableSingleObserver<User> userDisposableSingleObserver;

    @Inject
    public LoggedUser(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getLoggedUser(BaseCallback<User> callback) {
        userDisposableSingleObserver = new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No logged user");
            }
        };
        if (!compositeDisposable.isDisposed()){
            userSingle = dataRepository.getLoggedUser();
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
