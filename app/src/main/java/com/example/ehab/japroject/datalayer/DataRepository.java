package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.local.LocalRepository;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.remote.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Single;

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
}
