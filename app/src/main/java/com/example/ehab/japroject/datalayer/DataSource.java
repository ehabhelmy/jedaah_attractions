package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.allevents.AllEventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.example.ehab.japroject.datalayer.pojo.response.order.OrderResponse;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData(boolean fresh);

    Single<EventsResponse> getTopEvents(boolean fresh);

    Single<VenuesResponse> getTopVenues(boolean fresh);

    Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh);

    Single<VenuesResponse> getNearByVenues(LatLng latLng,boolean fresh);

    Single<Category> getCategories(boolean fresh);

    Single<EventsResponse> getTodayEvents(boolean fresh);

    Single<EventsResponse> getWeekEvents(boolean fresh);

    Single<AllEventsResponse> getAllEvents(boolean fresh,String newUrl);

    Single<EventsResponse> getLikedEvents(boolean fresh);

    Single<EventInnerResponse> getEventDetails(int id);

    Single<LoginResponse> login(String email,String password);

    Single<LoginResponse> sociaLogin(String facebookId,String googleId,String email);

    String getToken();

    void clearToken();

    Single<LoginResponse> register(String userName, String email, String password, String mobile, File image);

    Single<LikeResponse> like(int id);

    Single<LikeResponse> likeVenues(int id);

    Single<OrderResponse> order(String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total);

    Single<HistoryEvents> getUpcomingEvents();

    Single<HistoryEvents> getPastEvents();

    void saveTopEvents(EventsResponse eventsResponse);

    void saveNearByEvents(EventsResponse eventsResponse);

    void saveTopVenues(VenuesResponse venuesResponse);

    void saveNearByVenues(VenuesResponse venuesResponse);

    void saveCategories(Category categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    void saveWeekEvents(EventsResponse eventsResponse);

    void saveAllEvents(AllEventsResponse eventsResponse);

    void saveLikedEvents(EventsResponse eventsResponse);

    void saveLoggedUser(User user);

    Single<User> getLoggedUser();

    void saveToken(String token);

    Single<ProfileResponse> getProfile(boolean fresh);

    void saveProfile(ProfileResponse profileResponse);
}
