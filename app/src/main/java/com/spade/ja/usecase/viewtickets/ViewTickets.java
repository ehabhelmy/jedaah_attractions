package com.spade.ja.usecase.viewtickets;

import android.accounts.NetworkErrorException;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.viewtickets.Datum;
import com.spade.ja.datalayer.pojo.response.viewtickets.ViewTicketResponse;
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
 * Created by ehab on 3/17/18.
 */

public class ViewTickets implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<ViewTicketResponse> viewTicketResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<ViewTicketResponse> viewTicketResponseDisposableSingleObserver;

    @Inject
    public ViewTickets(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void viewTickets(BaseCallback<Datum> callback, String startTime,String endTime,String date,int id){
        viewTicketResponseDisposableSingleObserver = new DisposableSingleObserver<ViewTicketResponse>() {
            @Override
            public void onSuccess(ViewTicketResponse venuesInnerResponse) {
                if (venuesInnerResponse.getSuccess()) {
                    callback.onSuccess(venuesInnerResponse.getData().get(0));
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof NetworkErrorException) {
                    callback.onError(JaApplication.context.getString(R.string.connection));
                }else {
                    callback.onError(e.getMessage());
                }
            }
        };
        if (!compositeDisposable.isDisposed()){
            viewTicketResponseSingle = dataRepository.viewAttractionTickets(startTime, endTime, date,id);
            disposable = viewTicketResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(viewTicketResponseDisposableSingleObserver);
        }
    }

    @Override
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.remove(disposable);
        }
    }
}
