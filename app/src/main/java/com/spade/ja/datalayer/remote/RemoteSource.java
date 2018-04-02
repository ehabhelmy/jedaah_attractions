package com.spade.ja.datalayer.remote;

import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.datalayer.pojo.response.subscribe.SubscribeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.datalayer.pojo.response.viewtickets.ViewTicketResponse;

import java.io.File;
import java.util.List;

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

    Single<VenuesResponse> getTopAttractions(String token);

    Single<AllVenuesResponse> getAllVenues(String venueURL, String token);

    Single<AllVenuesResponse> getAllAttractions(String venueURL, String token);

    Single<VenuesResponse> getNearByVenues(LatLng latLng,String token);

    Single<VenuesResponse> getNearByAttractions(LatLng latLng,String token);

    Single<AllNearByResponse> getNearByAll(LatLng latLng, String token);

    Single<Category> getCategories();

    Single<EventsResponse> getTodayEvents(String token);

    Single<EventsResponse> getWeekEvents(String token);

    Single<AllEventsResponse> getAllEvents(String newURL,String token);

    Single<EventsResponse> getLikedEvents(String token);

    Single<VenuesResponse> getLikedVenues(String token);

    Single<VenuesResponse> getLikedAttractions(String token);

    Single<EventInnerResponse> getEventDetails(int id,String token);

    Single<VenuesInnerResponse> getVenueDetails(int id,String token);

    Single<AttractionInnerResponse> getAttractionsDetails(int id,String token);

    Single<ViewTicketResponse> viewAttractionTickets(String startTime,String endTime,String date, String token);

    Single<LoginResponse> login(String email, String password);

    Single<LoginResponse> sociaLogin(String facebookId,String googleId,String email);

    Single<LoginResponse> register(String userName, String email, String password, String mobile, File image);

    Single<SubscribeResponse> subscribe(String token);

    Single<LoginResponse> edit(String token, String userName, String email,String dateOfBirth,String gender ,String password, String mobile, File image);

    Single<LikeResponse> like(int id , String token);

    Single<LikeResponse> likeVenues(int id , String token);

    Single<LikeResponse> likeAttractions(int id , String token);

    Single<ProfileResponse> getProfile(String token);

    Single<OrderResponse> order(String token,String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total);

    Single<HistoryEvents> getUpcomingEvents(String token);

    Single<HistoryEvents> getPastEvents(String token);

    Single<SMSResponse> sendSMS(String phone);

    Single<FilterEventsResponse> filterEvents(boolean weeklySuggest, List<Integer> categoryId, int date, Double lat, Double lng );

    Single<FilterVenuesResponse> filterVenues(boolean weeklySuggest, List<Integer> categoryId, Double lat, Double lng);

    Single<AllNearByResponse> search(String searchWord,List<String> types , List<Integer> categoryId);

    Single<ContactUsResponse> contactUs(String subject,String message , String token);

    Single<AttractionOrderResponse> orderAttraction(AttractionOrderRequest attractionOrderRequest, String token);

}
