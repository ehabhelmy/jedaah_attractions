package com.spade.ja.datalayer.remote;

import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface RemoteSource {

    // all apis in the application
    DataResponse getData();

    Single<EventsResponse> getTopEvents(String token);

    Single<EventsResponse> getNearByEvents(LatLng latLng,String token);

    Single<VenuesResponse> getTopVenues(String token);

    Single<AllVenuesResponse> getAllVenues(String venueURL, String token);

    Single<VenuesResponse> getNearByVenues(LatLng latLng,String token);

    Single<Category> getCategories();

    Single<EventsResponse> getTodayEvents(String token);

    Single<EventsResponse> getWeekEvents(String token);

    Single<AllEventsResponse> getAllEvents(String newURL,String token);

    Single<EventsResponse> getLikedEvents(String token);

    Single<EventInnerResponse> getEventDetails(int id);

    Single<VenuesInnerResponse> getVenueDetails(int id);

    Single<LoginResponse> login(String email, String password);

    Single<LoginResponse> sociaLogin(String facebookId,String googleId,String email);

    Single<LoginResponse> register(String userName, String email, String password, String mobile, File image);

    Single<LoginResponse> edit(String token, String userName, String email,String dateOfBirth,String gender ,String password, String mobile, File image);

    Single<LikeResponse> like(int id , String token);

    Single<LikeResponse> likeVenues(int id , String token);

    Single<ProfileResponse> getProfile(String token);

    Single<OrderResponse> order(String token,String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total);

    Single<HistoryEvents> getUpcomingEvents(String token);

    Single<HistoryEvents> getPastEvents(String token);

    Single<SMSResponse> sendSMS(String phone);
}
