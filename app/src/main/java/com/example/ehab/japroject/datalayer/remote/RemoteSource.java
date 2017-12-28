package com.example.ehab.japroject.datalayer.remote;

import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.usecase.login.Login;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

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

    Single<EventInnerResponse> getEventDetails(int id);

    Single<LoginResponse> login(String email, String password);

    Single<LoginResponse> sociaLogin(String facebookId,String googleId,String email);

    Single<LoginResponse> register(String userName, String email, String password, String mobile, File image);

    Single<LikeResponse> like(int id , String token);
}
