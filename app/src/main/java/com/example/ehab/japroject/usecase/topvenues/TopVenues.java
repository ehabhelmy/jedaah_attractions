package com.example.ehab.japroject.usecase.topvenues;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Roma on 1/14/2018.
 */

public class TopVenues implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<Category> categoriesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<Category> disposableSingleObserver;

    @Inject
    public TopVenues(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getCategories(BaseCallback<Category> callback, boolean fresh) {

        disposableSingleObserver = new DisposableSingleObserver<Category>() {
            @Override
            public void onSuccess(Category categories) {
                if (fresh){
                    dataRepository.saveCategories(categories);
                }
                callback.onSuccess(categories);
            }

            @Override
            public void onError(Throwable e) {
                if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
                }
                else {
                    dataRepository.getCategories(false);
                }
            }
        };

        if (!compositeDisposable.isDisposed()) {
            categoriesResponseSingle = dataRepository.getCategories(fresh);
            disposable = categoriesResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
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
