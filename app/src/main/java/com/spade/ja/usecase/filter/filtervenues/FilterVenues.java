package com.spade.ja.usecase.filter.filtervenues;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 2/21/18.
 */

public class FilterVenues implements Unsubscribable {
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<FilterVenuesResponse> filterVenuesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<FilterVenuesResponse> filterVenuesResponseDisposableSingleObserver;

    @Inject
    public FilterVenues(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void filterVenues(boolean weeklysuggest, List<Integer> categoryId, BaseCallback<FilterVenuesResponse> filterEventsResponseBaseCallback,double lat,double lng) {
        filterVenuesResponseDisposableSingleObserver = new DisposableSingleObserver<FilterVenuesResponse>() {
            @Override
            public void onSuccess(FilterVenuesResponse filterEventsResponse) {
                filterEventsResponseBaseCallback.onSuccess(filterEventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                filterEventsResponseBaseCallback.onError("No events at this moment");
            }
        };
        if (!compositeDisposable.isDisposed()){
            filterVenuesResponseSingle = dataRepository.filterVenues(weeklysuggest,categoryId,lat,lng);
            disposable = filterVenuesResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(filterVenuesResponseDisposableSingleObserver);
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
