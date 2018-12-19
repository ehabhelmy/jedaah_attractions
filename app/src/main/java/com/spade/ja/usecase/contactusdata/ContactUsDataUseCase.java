package com.spade.ja.usecase.contactusdata;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactUsDataUseCase implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<ContactUsDataResponse> contactUsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<ContactUsDataResponse> contactUsResponseDisposableSingleObserver;

    @Inject
    public ContactUsDataUseCase(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void contactUs( BaseCallback<ContactUsDataResponse> callback) {

        contactUsResponseDisposableSingleObserver = new DisposableSingleObserver<ContactUsDataResponse>() {
            @Override
            public void onSuccess(ContactUsDataResponse loginResponse) {
                callback.onSuccess(loginResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(JaApplication.context.getString(R.string.connection));
            }
        };

        if (!compositeDisposable.isDisposed()) {
            contactUsResponseSingle = dataRepository.getContactUsData();
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
