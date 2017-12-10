package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.remote.ServiceResponse;
import com.example.ehab.japroject.datalayer.remote.service.DataService;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.example.ehab.japroject.datalayer.remote.ServiceError.SUCCESS_CODE;
import static com.example.ehab.japroject.util.Constants.BASE_URL;

/**
 * Created by ehab on 12/2/17.
 */

public class LocalRepository implements LocalSource {

    private SharedPref sharedPref;

    @Inject
    public LocalRepository(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public DataResponse getData() {
        DataResponse dataResponse = (DataResponse) sharedPref.getObject(sharedPref.DATA_KEY,DataResponse.class);
        return dataResponse;
    }

    @Override
    public void saveData(DataResponse dataResponse) {
        sharedPref.saveObject(SharedPref.DATA_KEY,dataResponse);
    }
}
