package com.example.ehab.japroject.datalayer.remote;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.google.android.gms.maps.model.LatLng;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface RemoteSource {

    // all apis in the application
    DataResponse getData();

    Single<EventsResponse> getTopEvents();

    Single<EventsResponse> getNearByEvents(LatLng latLng);

    Single<Category> getCategories();

    Single<EventsResponse> getTodayEvents();

    Single<EventsResponse> getWeekEvents();

    Single<EventsResponse> getAllEvents();
}
