package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;

import java.util.List;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData();

    Single<EventsResponse> getTopEvents();

    Single<EventsResponse> getNearByEvents(LatLng latLng);

    Single<Category> getCategories();

    Single<EventsResponse> getTodayEvents();

    Single<EventsResponse> getWeekEvents();

    Single<EventsResponse> getAllEvents();

    Single<EventInnerResponse> getEventDetails(int id);

    Single<LoginResponse> login(String email,String password);

    String getToken();
}
