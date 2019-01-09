package com.spade.ja.datalayer;

import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.about.AboutUsResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.attractionconfirm.AttractionConfirmOrderResponse;
import com.spade.ja.datalayer.pojo.response.attractionhistory.AttractionOrderHistoryResponse;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.code.ResetCodeResponse;
import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.datalayer.pojo.response.subscribe.SubscribeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.spade.ja.datalayer.pojo.response.viewtickets.ViewTicketResponse;

import java.io.File;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData(boolean fresh);

    Single<EventsResponse> getTopEvents(boolean fresh);

    Single<VenuesResponse> getTopVenues(boolean fresh);

    Single<VenuesResponse> getTopAttractions(boolean fresh);

    Single<AllVenuesResponse> getAllVenues(boolean fresh, String venueURL);

    Single<AllVenuesResponse> getAllAttractions(boolean fresh, String venueURL);

    Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh);

    Single<VenuesResponse> getNearByVenues(LatLng latLng,boolean fresh);

    Single<VenuesResponse> getNearByAttractions(LatLng latLng,boolean fresh);

    Single<AllNearByResponse> getNearByAll(LatLng latLng, boolean fresh);

    Single<Category> getCategories(boolean fresh);

    Single<EventsResponse> getTodayEvents(boolean fresh);

    Single<EventsResponse> getWeekEvents(boolean fresh);

    Single<AllEventsResponse> getAllEvents(boolean fresh,String newUrl);

    Single<EventsResponse> getLikedEvents(boolean fresh);

    Single<VenuesResponse> getLikedVenues(boolean fresh);

    Single<VenuesResponse> getLikedAttractions(boolean fresh);

    Single<EventInnerResponse> getEventDetails(int id);

    Single<VenuesInnerResponse> getVenueDetails(int id);

    Single<AttractionInnerResponse> getAttractionsDetails(int id);

    Single<ViewTicketResponse> viewAttractionTickets(String startTime, String endTime, String date,int id);

    Single<LoginResponse> login(String email,String password);

    Single<LoginResponse> sociaLogin(String facebookId,String googleId,String email);

    String getToken();

    void clearToken();

    Single<LoginResponse> register(String userName, String email, String password, String mobile, File image);

    Single<LoginResponse> edit(String userName, String email,String dateOfBirth,String gender ,String password, String mobile, File image);

    Single<LikeResponse> like(int id);

    Single<LikeResponse> likeVenues(int id);

    Single<LikeResponse> likeAttractions(int id);

    Single<OrderResponse> order(String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total);

    Single<HistoryEvents> getUpcomingEvents();

    Single<HistoryEvents> getPastEvents();

    Single<AttractionOrderHistoryResponse> getUpcomingAttractions();

    Single<AttractionOrderHistoryResponse> getPastAttractions();

    Single<ContactUsResponse> contactUs(String subject, String message);

    Single<ContactUsResponse> cancelAttraction(int id);

    Single<ContactUsResponse> cancelEvent(int id);

    void saveTopEvents(EventsResponse eventsResponse);

    void saveNearByEvents(EventsResponse eventsResponse);

    void saveTopVenues(VenuesResponse venuesResponse);

    void saveTopAttractions(VenuesResponse venuesResponse);

    void saveAllVenues(AllVenuesResponse venuesResponse);

    void saveAllAttractions(AllVenuesResponse venuesResponse);

    void saveNearByVenues(VenuesResponse venuesResponse);

    void saveNearByAttractions(VenuesResponse venuesResponse);

    void saveNearByAll(AllNearByResponse venuesResponse);

    void saveCategories(Category categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    void saveWeekEvents(EventsResponse eventsResponse);

    void saveAllEvents(AllEventsResponse eventsResponse);

    void saveLikedEvents(EventsResponse eventsResponse);

    void saveLikedVenues(VenuesResponse eventsResponse);

    void saveLikedAttractions(VenuesResponse eventsResponse);

    void saveLoggedUser(User user);

    Single<User> getLoggedUser();

    void saveToken(String token);

    Single<ProfileResponse> getProfile(boolean fresh);

    void clearProfile();

    void saveProfile(ProfileResponse profileResponse);

    Single<SMSResponse> sendSMS(String phone);

    Single<FilterEventsResponse> filterEvents(boolean weeklySuggest, List<Integer> categoryId, int date, Double lat, Double lng );

    Single<FilterVenuesResponse> filterVenues(boolean weeklySuggest, List<Integer> categoryId , Double lat, Double lng);

    Single<FilterVenuesResponse> filterAttracions(boolean weeklySuggest, List<Integer> categoryId , Double lat, Double lng);

    Single<AllNearByResponse> filterCatsExplore(List<Integer> categoryId);

    Single<AllNearByResponse> filterCatsDirectories(List<Integer> categoryId);

    Single<AllNearByResponse> search(String searchWord,List<String> types , List<Integer> categoryId);

    Single<AttractionOrderResponse> orderAttraction(AttractionOrderRequest attractionOrderRequest);

    Single<SubscribeResponse> subscribe();

    Single<ResetCodeResponse> getCode(String email);

    Single<ContactUsResponse> resetPassword(String password,String code);

    Single<AboutUsResponse> about();

    Single<EventChangeStatusResponse> changeOrderCreditEvent(String orderId, String status);

    Single<AttractionConfirmOrderResponse> changeOrderCreditAttraction(String orderId, String status);

    Single<ContactUsDataResponse> getContactUsData();

    boolean isFirstInstall();

    void walkThroughAppeared();
}
