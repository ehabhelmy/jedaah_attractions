package com.example.ehab.japroject.usecase.like;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
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

public class Like implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<LikeResponse> likeResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<LikeResponse> likeResponseDisposableSingleObserver;

    @Inject
    public Like(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void like(int id, BaseCallback<LikeResponse> callback){
        likeResponseDisposableSingleObserver = new DisposableSingleObserver<LikeResponse>() {
            @Override
            public void onSuccess(LikeResponse likeResponse) {
                //TODO : handle succes of like
                if (likeResponse.getSuccess()){
                    callback.onSuccess(likeResponse);
                }else {
                    callback.onError(likeResponse.getMsg().getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                //TODO : show Error important
                callback.onError("Server Error");
            }
        };
        if (!compositeDisposable.isDisposed()){
            likeResponseSingle = dataRepository.like(id);
            disposable = likeResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(likeResponseDisposableSingleObserver);
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
