package com.spade.ja.usecase.filter.filterevents;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
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

public class FilterEvents implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<FilterEventsResponse> filterEventsResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<FilterEventsResponse> filterEventsResponseDisposableSingleObserver;

    @Inject
    public FilterEvents(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void filterEvents(boolean weeklysuggest, List<Integer> categoryId,Double lat,Double lng, int monthNumber, BaseCallback<FilterEventsResponse> filterEventsResponseBaseCallback) {
        filterEventsResponseDisposableSingleObserver = new DisposableSingleObserver<FilterEventsResponse>() {
            @Override
            public void onSuccess(FilterEventsResponse filterEventsResponse) {
                filterEventsResponseBaseCallback.onSuccess(filterEventsResponse);
            }

            @Override
            public void onError(Throwable e) {
                filterEventsResponseBaseCallback.onError("No events at this moment");
            }
        };
        if (!compositeDisposable.isDisposed()){
            filterEventsResponseSingle = dataRepository.filterEvents(weeklysuggest,categoryId,monthNumber,lat,lng);
            disposable = filterEventsResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(filterEventsResponseDisposableSingleObserver);
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
