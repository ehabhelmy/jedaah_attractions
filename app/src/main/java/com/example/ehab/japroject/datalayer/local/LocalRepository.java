package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.util.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

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
        DataResponse dataResponse = (DataResponse) sharedPref.getObject(sharedPref.DATA_KEY, DataResponse.class);
        return dataResponse;
    }

    @Override
    public void saveData(DataResponse dataResponse) {
        sharedPref.saveObject(SharedPref.DATA_KEY, dataResponse);
    }

    @Override
    public Single<EventsResponse> getTopEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.TOP_EVENTS,EventsResponse.class);
        Single<EventsResponse> topEventsResponseSingle = Single.create(e -> {
           if (eventsResponse != null){
               e.onSuccess(eventsResponse);
           }else{
               e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
           }
        });
        return topEventsResponseSingle;
    }

    @Override
    public void saveTopEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.TOP_EVENTS, eventsResponse);
    }

    @Override
    public Single<EventsResponse> getNearByEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.NEARBY_EVENTS,EventsResponse.class);
        Single<EventsResponse> NearByEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return NearByEventsResponseSingle;
    }

    @Override
    public void saveNearByEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.NEARBY_EVENTS, eventsResponse);
    }

    @Override
    public Single<List<Category>> getCategories() {
        List<Category> categoriesResponse = (List<Category>) sharedPref.getCategoryList(SharedPref.CATEGORIES);
        Single<List<Category>> categoriesResponseSingle = Single.create(e -> {
            if (categoriesResponse != null) {
                e.onSuccess(categoriesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return categoriesResponseSingle;
    }

    @Override
    public void saveCategories(List<Category> categoriesResponse) {
        sharedPref.saveObject(SharedPref.CATEGORIES, categoriesResponse);
    }
}
