package com.spade.ja.datalayer;

import com.spade.ja.datalayer.local.LocalRepository;
import com.spade.ja.datalayer.pojo.response.DataResponse;
import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.order.OrderResponse;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.spade.ja.datalayer.remote.RemoteRepository;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public class DataRepository implements DataSource {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<DataResponse> getData(boolean fresh) {
        //TODO : first check if there is internet connection ..
        //TODO : if there is internet connection then call remoteRepository.getData then call localRepository.saveData(Key,BaseModel)
        //TODO : if there isn't internet connection then call localRepository.getData
        //TODO : create Single that sends BaseModel in on success.
        //TODO : incase of BaseModel == null ----> call on Error.
        return null;
    }

    @Override
    public Single<EventsResponse> getTopEvents(boolean fresh) {
        if (fresh){
            return remoteRepository.getTopEvents(getToken());
        } else {
            return localRepository.getTopEvents();
        }
    }

    @Override
    public Single<VenuesResponse> getTopVenues(boolean fresh) {
        if (fresh){
            return remoteRepository.getTopVenues(getToken());
        } else {
            return localRepository.getTopVenues();
        }
    }

    @Override
    public Single<AllVenuesResponse> getAllVenues(boolean fresh, String venueURL) {
        if (fresh){
            return remoteRepository.getAllVenues(venueURL,getToken());
        } else {
            return localRepository.getAllVenues();
        }
    }

    @Override
    public Single<EventsResponse> getNearByEvents(LatLng latLng,boolean fresh) {
        if (fresh){
            return remoteRepository.getNearByEvents(latLng,getToken());
        } else {
            return localRepository.getNearByEvents();
        }
    }

    @Override
    public Single<VenuesResponse> getNearByVenues(LatLng latLng, boolean fresh) {
        if (fresh){
            return remoteRepository.getNearByVenues(latLng,getToken());
        } else {
            return localRepository.getNearByVenues();
        }
    }

    @Override
    public Single<AllNearByResponse> getNearByAll(LatLng latLng, boolean fresh) {
        if (fresh){
            return remoteRepository.getNearByAll(latLng,getToken());
        } else {
            return localRepository.getNearByAll();
        }
    }

    @Override
    public Single<Category> getCategories(boolean fresh) {
        if (fresh) {
            return remoteRepository.getCategories();
        } else {
            return localRepository.getCategories();
        }
    }

    @Override
    public Single<EventsResponse> getTodayEvents(boolean fresh) {
        if (fresh) {
            return remoteRepository.getTodayEvents(getToken());
        }else {
            return localRepository.getTodayEvents();
        }
    }

    @Override
    public Single<EventsResponse> getWeekEvents(boolean fresh) {
        if (fresh) {
            return remoteRepository.getWeekEvents(getToken());
        }else {
            return localRepository.getWeekEvents();
        }
    }

    @Override
    public Single<AllEventsResponse> getAllEvents(boolean fresh,String newUrl) {
        if (fresh){
            return remoteRepository.getAllEvents(newUrl,getToken());
        } else {
            return localRepository.getAllEvents();
        }
    }

    @Override
    public Single<EventsResponse> getLikedEvents(boolean fresh) {
        if (fresh) {
            return remoteRepository.getLikedEvents(getToken());
        } else {
            return localRepository.getLikedEvents();
        }
    }

    @Override
    public Single<EventInnerResponse> getEventDetails(int id) {
        return remoteRepository.getEventDetails(id);
    }

    @Override
    public Single<VenuesInnerResponse> getVenueDetails(int id) {
        return remoteRepository.getVenueDetails(id);
    }

    @Override
    public Single<LoginResponse> login(String email, String password) {
        return remoteRepository.login(email, password);
    }

    @Override
    public Single<LoginResponse> sociaLogin(String facebookId,String googleId, String email) {
        return remoteRepository.sociaLogin(facebookId,googleId, email);
    }

    @Override
    public String getToken() {
        return localRepository.getToken();
    }

    @Override
    public void clearToken() {
        localRepository.clearToken();
    }

    @Override
    public Single<LoginResponse> register(String userName, String email, String password, String mobile, File image) {
        return remoteRepository.register(userName, email, password, mobile, image);
    }

    @Override
    public Single<LoginResponse> edit(String userName, String email, String dateOfBirth, String gender, String password, String mobile, File image) {
        return remoteRepository.edit(getToken(),userName,email,dateOfBirth,gender,password,mobile,image);
    }

    @Override
    public Single<LikeResponse> like(int id) {
        if (getToken() == null) {
            return null;
        }
        return remoteRepository.like(id ,getToken());
    }

    @Override
    public Single<LikeResponse> likeVenues(int id) {
        if (getToken() == null) {
            return null;
        }
        return remoteRepository.likeVenues(id ,getToken());
    }

    @Override
    public Single<OrderResponse> order(String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total) {
        return remoteRepository.order(getToken(),name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total);
    }

    @Override
    public Single<HistoryEvents> getUpcomingEvents() {
        return remoteRepository.getUpcomingEvents(getToken());
    }

    @Override
    public Single<HistoryEvents> getPastEvents() {
        return remoteRepository.getPastEvents(getToken());
    }

    @Override
    public void saveTopEvents(EventsResponse eventsResponse) {
        localRepository.saveTopEvents(eventsResponse);
    }

    @Override
    public void saveNearByEvents(EventsResponse eventsResponse) {
        localRepository.saveNearByEvents(eventsResponse);
    }

    @Override
    public void saveTopVenues(VenuesResponse venuesResponse) {
        localRepository.saveTopVenues(venuesResponse);
    }

    @Override
    public void saveAllVenues(AllVenuesResponse venuesResponse) {
        localRepository.saveAllVenues(venuesResponse);
    }

    @Override
    public void saveNearByVenues(VenuesResponse venuesResponse) {
        localRepository.saveNearByVenues(venuesResponse);
    }

    @Override
    public void saveNearByAll(AllNearByResponse venuesResponse) {
        localRepository.saveAllNearBy(venuesResponse);
    }

    @Override
    public void saveCategories(Category categoriesResponse) {
        localRepository.saveCategories(categoriesResponse);
    }

    @Override
    public void saveTodayEvents(EventsResponse eventsResponse) {
        localRepository.saveTodayEvents(eventsResponse);
    }

    @Override
    public void saveWeekEvents(EventsResponse eventsResponse) {
        localRepository.saveWeekEvents(eventsResponse);
    }

    @Override
    public void saveAllEvents(AllEventsResponse eventsResponse) {
        localRepository.saveAllEvents(eventsResponse);
    }

    @Override
    public void saveLikedEvents(EventsResponse eventsResponse) {
        localRepository.saveLikedEvents(eventsResponse);
    }

    @Override
    public void saveLoggedUser(User user) {
        localRepository.saveLoggedUser(user);
    }

    @Override
    public Single<User> getLoggedUser() {
        return localRepository.getUserProfile();
    }

    @Override
    public void saveToken(String token) {
        localRepository.saveToken(token);
    }

    @Override
    public Single<ProfileResponse> getProfile(boolean fresh) {
        return remoteRepository.getProfile(localRepository.getToken());
    }

    @Override
    public void saveProfile(ProfileResponse profileResponse) {
        localRepository.saveProfile(profileResponse);
    }

    @Override
    public Single<SMSResponse> sendSMS(String phone) {
        return remoteRepository.sendSMS(phone);
    }

}
