package com.example.ehab.japroject.usecase.categories;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.category.CategoryCallback;
import com.example.ehab.japroject.usecase.Unsubscribable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Romisaa.Attia on 12/12/2017.
 */

public class Categories implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<List<Category>> categoriesResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<List<Category>> disposableSingleObserver;

    @Inject
    public Categories(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getCategories(CategoryCallback<List<Category>> callback) {

        disposableSingleObserver = new DisposableSingleObserver<List<Category>>() {


            @Override
            public void onSuccess(List<Category> categories) {
                callback.onSuccess(categories);
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        if (!compositeDisposable.isDisposed()) {
            categoriesResponseSingle = dataRepository.getCategories();
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
