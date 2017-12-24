package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData(boolean fresh);

    Single<EventsResponse> getTopEvents(boolean fresh);

    Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh);

    Single<Category> getCategories(boolean fresh);

    Single<EventsResponse> getTodayEvents(boolean fresh);

    Single<EventsResponse> getWeekEvents(boolean fresh);

    Single<EventsResponse> getAllEvents(boolean fresh);

    Single<EventInnerResponse> getEventDetails(int id);

    Single<LoginResponse> login(String email,String password);

    String getToken();

    Single<RegisterationResponse> register(String userName, String email, String password, String mobile, File image);

    void saveTopEvents(EventsResponse eventsResponse);

    void saveNearByEvents(EventsResponse eventsResponse);

    void saveCategories(Category categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    void saveWeekEvents(EventsResponse eventsResponse);

    void saveAllEvents(EventsResponse eventsResponse);

    void saveLoggedUser(User user);

    void saveToken(String token);

}
