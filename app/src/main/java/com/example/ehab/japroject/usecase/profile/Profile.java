package com.example.ehab.japroject.usecase.profile;

import com.example.ehab.japroject.datalayer.DataRepository;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
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
 * Created by Romisaa on 12/26/2017.
 */

public class Profile implements Unsubscribable {

    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Single<ProfileResponse> profileResponseSingle;
    private Disposable disposable;
    private DisposableSingleObserver<ProfileResponse> profileResponseDisposableSingleObserver;


    @Inject
    public Profile(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getProfile(BaseCallback<ProfileResponse> callback, boolean fresh) {
        profileResponseDisposableSingleObserver = new DisposableSingleObserver<ProfileResponse>() {
            @Override
            public void onSuccess(ProfileResponse profileResponse) {
                if (fresh){
                    dataRepository.saveProfile(profileResponse);
                }
                callback.onSuccess(profileResponse);
            }

            @Override
            public void onError(Throwable e) {
                //if(e.getMessage()!= null && e.getMessage().equals(Constants.ERROR_NOT_CACHED)){
                    callback.onError(e.getMessage());
               // }
//                else {
//                    dataRepository.getProfile(false);
//                }
            }
        };
        if (!compositeDisposable.isDisposed()) {
            profileResponseSingle = dataRepository.getProfile(fresh);
            disposable = profileResponseSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(profileResponseDisposableSingleObserver);
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
