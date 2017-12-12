package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.TopEventsResponse;
import com.example.ehab.japroject.datalayer.remote.RemoteRepository;
import static com.example.ehab.japroject.util.NetworkUtils.isConnected;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ehab on 12/2/17.
 */

public class DataRepository implements DataSource {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<DataResponse> getData() {
        //TODO : first check if there is internet connection ..
        //TODO : if there is internet connection then call remoteRepository.getData then call localRepository.saveData(Key,BaseModel)
        //TODO : if there isn't internet connection then call localRepository.getData
        //TODO : create Single that sends BaseModel in on success.
        //TODO : incase of BaseModel == null ----> call on Error.
        return null;
    }

    @Override
    public Single<TopEventsResponse> getTopEvents() {
        if (isConnected(JaApplication.getContext())){
            remoteRepository.getTopEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<TopEventsResponse>() {
                        @Override
                        public void onSuccess(TopEventsResponse topEventsResponse) {
                            localRepository.saveTopEvents(topEventsResponse);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            return remoteRepository.getTopEvents();
        }else{
            return localRepository.getTopEvents();
        }
    }
}
