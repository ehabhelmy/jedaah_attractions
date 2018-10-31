package com.spade.ja.usecase.code;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.code.ResetCodeResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Code implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<ResetCodeResponse> contactUsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<ResetCodeResponse> contactUsResponseDisposableSingleObserver;

    @Inject
    public Code(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getCode(String email, BaseCallback<ResetCodeResponse> callback) {

        contactUsResponseDisposableSingleObserver = new DisposableSingleObserver<ResetCodeResponse>() {
            @Override
            public void onSuccess(ResetCodeResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    callback.onSuccess(loginResponse);
                }else {
                    callback.onError("Server Error");
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(JaApplication.context.getString(R.string.email));
            }
        };

        if (!compositeDisposable.isDisposed()) {
            contactUsResponseSingle = dataRepository.getCode(email);
            disposable = contactUsResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(contactUsResponseDisposableSingleObserver);
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
