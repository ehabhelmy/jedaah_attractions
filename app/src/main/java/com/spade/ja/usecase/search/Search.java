package com.spade.ja.usecase.search;

import com.spade.ja.datalayer.DataRepository;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
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
 * Created by ehab on 3/20/18.
 */

public class Search implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<AllNearByResponse> single;
    private Disposable disposable;
    private DisposableSingleObserver<AllNearByResponse> singleObserver;

    @Inject
    public Search(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void search(String searchKeyword,List<String> types, List<Integer> categoryId, BaseCallback<AllNearByResponse> callback) {
        singleObserver = new DisposableSingleObserver<AllNearByResponse>() {
            @Override
            public void onSuccess(AllNearByResponse allNearByResponse) {
                callback.onSuccess(allNearByResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError("No Results Found");
            }
        };
        if (!compositeDisposable.isDisposed()){
            single = dataRepository.search(searchKeyword,types,categoryId);
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
