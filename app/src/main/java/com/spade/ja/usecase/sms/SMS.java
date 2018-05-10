package com.spade.ja.usecase.sms;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 1/25/18.
 */

public class SMS implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<SMSResponse> single;
    private Disposable disposable;
    private DisposableSingleObserver<SMSResponse> singleObserver;

    @Inject
    public SMS(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void sendSms(String phone) {
        singleObserver = new DisposableSingleObserver<SMSResponse>() {
            @Override
            public void onSuccess(SMSResponse allNearByResponse) {
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        if (!compositeDisposable.isDisposed()){
            single = dataRepository.sendSMS(phone);
            disposable = single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(singleObserver);
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
