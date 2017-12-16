package com.example.ehab.japroject.datalayer.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.remote.service.AllEventsService;
import com.example.ehab.japroject.datalayer.remote.service.CategoriesService;
import com.example.ehab.japroject.datalayer.remote.service.DataService;
import com.example.ehab.japroject.datalayer.remote.service.NearByEventsService;
import com.example.ehab.japroject.datalayer.remote.service.TopEventsService;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

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
    public Single<EventsResponse> getTopEvents() {
        Single<EventsResponse> topEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            TopEventsService topEventsService = serviceGenerator.createService(TopEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(topEventsService.getTopEvents(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return topEventsSingle;
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng) {
        Single<EventsResponse> nearByEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            NearByEventsService nearByEventsService = serviceGenerator.createService(NearByEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(nearByEventsService.getNearbyEvents(new NearbyEventsRequest(latLng.latitude+"",latLng.longitude+"")), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return nearByEventsSingle;
    }

    @Override
    public Single<List<Category>> getCategories() {
        Single<List<Category>> categoriesResponseSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            CategoriesService categoriesService = serviceGenerator.createService(CategoriesService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(categoriesService.getCategories(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                List<Category> categoriesResponse = (List<Category>) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(categoriesResponse);
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
        return categoriesResponseSingle;
    }

    @Override
    public Single<EventsResponse> getAllEvents() {
        Single<EventsResponse> allEventsSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(JaApplication.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            AllEventsService allEventsService = serviceGenerator.createService(AllEventsService.class, BASE_URL);
                            ServiceResponse serviceResponse = processCall(allEventsService.getAllEvents(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                EventsResponse eventsResponse = (EventsResponse) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(eventsResponse);
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
        return allEventsSingle;
    }
}
