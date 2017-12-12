package com.example.ehab.japroject.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.ErrorModel;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.TopEventsResponse;
import com.example.ehab.japroject.datalayer.remote.service.DataService;
import com.example.ehab.japroject.datalayer.remote.service.TopEventsService;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;

import static com.example.ehab.japroject.datalayer.remote.ServiceError.NETWORK_ERROR;
import static com.example.ehab.japroject.datalayer.remote.ServiceError.SUCCESS_CODE;
import static com.example.ehab.japroject.util.Constants.BASE_URL;
import static com.example.ehab.japroject.util.Constants.ERROR_UNDEFINED;
import static com.example.ehab.japroject.util.NetworkUtils.isConnected;

/**
 * Created by ehab on 12/2/17.
 */

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        if (!isConnected(JaApplication.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        try {
            retrofit2.Response response = call.execute();
            Gson gson = new Gson();
            if (response == null) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }

    @Override
    public DataResponse getData() {
        DataService dataService = serviceGenerator.createService(DataService.class, BASE_URL);
        ServiceResponse serviceResponse = processCall(dataService.getData(), false);
        if (serviceResponse.getCode() == SUCCESS_CODE){
            DataResponse dataResponse = (DataResponse) serviceResponse.getData();
            return dataResponse;
        }else{
            return null;
        }
    }

    @Override
    public Single<TopEventsResponse> getTopEvents() {
        Single<TopEventsResponse> userResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopEventsService topEventsService = serviceGenerator.createService(TopEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topEventsService.getTopEvents(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                TopEventsResponse topEventsResponse = (TopEventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(topEventsResponse);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return userResponseSingle;
    }
}
