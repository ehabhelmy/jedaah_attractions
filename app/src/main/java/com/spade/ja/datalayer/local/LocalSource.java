package com.spade.ja.datalayer.local;

import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;

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

    Single<VenuesResponse> getTopVenues();

    Single<AllVenuesResponse> getAllVenues();

    void saveTopVenues(VenuesResponse venuesResponse);

    void saveAllVenues(AllVenuesResponse venuesResponse);

    Single<VenuesResponse> getNearByVenues();

    void saveNearByVenues(VenuesResponse venuesResponse);

    Single<AllNearByResponse> getNearByAll();

    void saveAllNearBy(AllNearByResponse allNearByResponse);

    Single<Category> getCategories();

    void saveCategories(Category categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getTodayEvents();

    void saveWeekEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getWeekEvents();

    Single<AllEventsResponse> getAllEvents();

    void saveAllEvents(AllEventsResponse eventsResponse);

    Single<EventsResponse> getLikedEvents();

    void saveLikedEvents(EventsResponse eventsResponse);

    void saveLoggedUser(User user);

    Single<User> getUserProfile();

    void saveToken(String token);

    String getToken();

    void clearToken();

    Single<ProfileResponse> getProfile();

    void saveProfile(ProfileResponse profileResponse);
}
