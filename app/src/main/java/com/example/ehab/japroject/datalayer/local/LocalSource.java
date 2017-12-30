package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface LocalSource {
    // all apis cached in the application

    DataResponse getData();

    void saveData(DataResponse dataResponse);

    Single<EventsResponse> getTopEvents();

    void saveTopEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getNearByEvents();

    void saveNearByEvents(EventsResponse eventsResponse);

    Single<Category> getCategories();

    void saveCategories(Category categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getTodayEvents();

    void saveWeekEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getWeekEvents();

    Single<EventsResponse> getAllEvents();

    void saveAllEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getLikedEvents();

    void saveLikedEvents(EventsResponse eventsResponse);

    void saveLoggedUser(User user);

    Single<User> getUserProfile();

    void saveToken(String token);

    String getToken();

    Single<ProfileResponse> getProfile();

    void saveProfile(ProfileResponse profileResponse);
}
