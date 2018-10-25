package com.spade.ja.usecase.paymentcredit;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.attractionconfirm.AttractionConfirmOrderResponse;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PaymentCreditAttractionUseCase implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AttractionConfirmOrderResponse> single;
    private Disposable disposable;
    private DisposableSingleObserver<AttractionConfirmOrderResponse> singleObserver;

    @Inject
    public PaymentCreditAttractionUseCase(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void changeCreditCardOrder(String orderId, String status, BaseCallback<AttractionConfirmOrderResponse> onResponseRecieved) {
        singleObserver = new DisposableSingleObserver<AttractionConfirmOrderResponse>() {
            @Override
            public void onSuccess(AttractionConfirmOrderResponse model) {
                if (model != null){
                    onResponseRecieved.onSuccess(model);
                }
            }

            @Override
            public void onError(Throwable e) {
                onResponseRecieved.onError(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()){
            single = dataRepository.changeOrderCreditAttraction(orderId,status);
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