package com.spade.ja.usecase.contactus;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
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
 * Created by ehab on 3/12/18.
 */

public class ContactUs implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<ContactUsResponse> contactUsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<ContactUsResponse> contactUsResponseDisposableSingleObserver;

    @Inject
    public ContactUs(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void contactUs(String subject,String message, BaseCallback<ContactUsResponse> callback) {

        contactUsResponseDisposableSingleObserver = new DisposableSingleObserver<ContactUsResponse>() {
            @Override
            public void onSuccess(ContactUsResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    callback.onSuccess(loginResponse);
                }else {
                    callback.onError("Server Error");
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No Data Connection");
            }
        };

        if (!compositeDisposable.isDisposed()) {
            contactUsResponseSingle = dataRepository.contactUs(subject, message);
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
