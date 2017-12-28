package com.example.ehab.japroject.usecase.order;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.order.Data;
import com.example.ehab.japroject.datalayer.pojo.response.order.OrderResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 12/25/17.
 */

public class Order implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<OrderResponse> orderResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<OrderResponse> orderResponseDisposableSingleObserver;

    @Inject
    public Order(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void order(BaseCallback<Data> callback, String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total){
        orderResponseDisposableSingleObserver = new DisposableSingleObserver<OrderResponse>() {
            @Override
            public void onSuccess(OrderResponse orderResponse) {
                if (orderResponse.getSuccess()){
                    callback.onSuccess(orderResponse.getData());
                }else {
                    callback.onError(orderResponse.getMsg().getErrors().getUserNationalId().get(0));
                }
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()){
            orderResponseSingle = dataRepository.order(name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total);
            disposable = orderResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(orderResponseDisposableSingleObserver);
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
