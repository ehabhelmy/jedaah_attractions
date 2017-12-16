package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData();

    Single<EventsResponse> getTopEvents();

    Single<EventsResponse> getNearByEvents(LatLng latLng);

    Single<List<Category>> getCategories();

    Single<EventsResponse> getTodayEvents();

    Single<EventsResponse> getWeekEvents();
}
