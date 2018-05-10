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
import com.spade.ja.util.Constants;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public class LocalRepository implements LocalSource {

    private SharedPref sharedPref;

    @Inject
    public LocalRepository(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public DataResponse getData() {
        DataResponse dataResponse = (DataResponse) sharedPref.getObject(sharedPref.DATA_KEY, DataResponse.class);
        return dataResponse;
    }

    @Override
    public void saveData(DataResponse dataResponse) {
        sharedPref.saveObject(SharedPref.DATA_KEY, dataResponse);
    }

    @Override
    public Single<EventsResponse> getTopEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.TOP_EVENTS, EventsResponse.class);
        Single<EventsResponse> topEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null) {
                e.onSuccess(eventsResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return topEventsResponseSingle;
    }

    @Override
    public void saveTopEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.TOP_EVENTS, eventsResponse);
    }

    @Override
    public Single<EventsResponse> getNearByEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.NEARBY_EVENTS, EventsResponse.class);
        Single<EventsResponse> NearByEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null) {
                e.onSuccess(eventsResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return NearByEventsResponseSingle;
    }

    @Override
    public void saveNearByEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.NEARBY_EVENTS, eventsResponse);
    }

    @Override
    public Single<VenuesResponse> getTopVenues() {
        VenuesResponse venuesResponse = (VenuesResponse) sharedPref.getObject(sharedPref.TOP_VENUES, VenuesResponse.class);
        Single<VenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getTopAttractions() {
        VenuesResponse venuesResponse = (VenuesResponse) sharedPref.getObject(sharedPref.TOP_ATTRACTIONS, VenuesResponse.class);
        Single<VenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public Single<AllVenuesResponse> getAllVenues() {
        AllVenuesResponse venuesResponse = (AllVenuesResponse) sharedPref.getObject(sharedPref.ALL_VENUES, AllVenuesResponse.class);
        Single<AllVenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public Single<AllVenuesResponse> getAllAttractions() {
        AllVenuesResponse venuesResponse = (AllVenuesResponse) sharedPref.getObject(sharedPref.ALL_ATTRACTIONS, AllVenuesResponse.class);
        Single<AllVenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public void saveTopVenues(VenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.TOP_VENUES, venuesResponse);
    }

    @Override
    public void saveTopAttractions(VenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.TOP_ATTRACTIONS, venuesResponse);
    }

    @Override
    public void saveAllVenues(AllVenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.ALL_VENUES, venuesResponse);
    }

    @Override
    public void saveAllAttractions(AllVenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.ALL_ATTRACTIONS, venuesResponse);
    }

    @Override
    public Single<VenuesResponse> getNearByVenues() {
        VenuesResponse venuesResponse = (VenuesResponse) sharedPref.getObject(sharedPref.NEARBY_VENUES, VenuesResponse.class);
        Single<VenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public Single<VenuesResponse> getNearByAttractions() {
        VenuesResponse venuesResponse = (VenuesResponse) sharedPref.getObject(sharedPref.NEARBY_ATTRACTIONS, VenuesResponse.class);
        Single<VenuesResponse> venuesResponseSingle = Single.create(e -> {
            if (venuesResponse != null) {
                e.onSuccess(venuesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return venuesResponseSingle;
    }

    @Override
    public void saveNearByVenues(VenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.NEARBY_VENUES, venuesResponse);
    }

    @Override
    public void saveNearByAttractions(VenuesResponse venuesResponse) {
        sharedPref.saveObject(SharedPref.NEARBY_ATTRACTIONS, venuesResponse);
    }

    @Override
    public Single<AllNearByResponse> getNearByAll() {
        AllNearByResponse allNearByResponse = (AllNearByResponse) sharedPref.getObject(sharedPref.ALL_NEARBY, AllNearByResponse.class);
        Single<AllNearByResponse> allNearByResponseSingle = Single.create(e -> {
            if (allNearByResponse != null) {
                e.onSuccess(allNearByResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return allNearByResponseSingle;
    }

    @Override
    public void saveAllNearBy(AllNearByResponse allNearByResponse) {
        sharedPref.saveObject(SharedPref.ALL_NEARBY, allNearByResponse);
    }

    @Override
    public Single<Category> getCategories() {
        Category categoriesResponse = (Category) sharedPref.getObject(SharedPref.CATEGORIES,Category.class);
        Single<Category> categoriesResponseSingle = Single.create(e -> {
            if (categoriesResponse != null) {
                e.onSuccess(categoriesResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return categoriesResponseSingle;
    }

    @Override
    public void saveCategories(Category categoriesResponse) {
        sharedPref.saveObject(SharedPref.CATEGORIES, categoriesResponse);
    }

    @Override
    public void saveTodayEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.TADAY_EVENTS, eventsResponse);
    }

    @Override
    public Single<EventsResponse> getTodayEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(SharedPref.TADAY_EVENTS, EventsResponse.class);
        Single<EventsResponse> todayEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null) {
                e.onSuccess(eventsResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return todayEventsResponseSingle;
    }

    @Override
    public void saveWeekEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.WEEK_EVENTS, eventsResponse);
    }

    @Override
    public Single<EventsResponse> getWeekEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(SharedPref.WEEK_EVENTS, EventsResponse.class);
        Single<EventsResponse> weekEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null) {
                e.onSuccess(eventsResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return weekEventsResponseSingle;
    }

    @Override
    public Single<AllEventsResponse> getAllEvents() {
        AllEventsResponse eventsResponse = (AllEventsResponse) sharedPref.getObject(sharedPref.ALL_EVENTS,AllEventsResponse.class);
        Single<AllEventsResponse> allEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return allEventsResponseSingle;
    }

    @Override
    public void saveAllEvents(AllEventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.ALL_EVENTS,eventsResponse);
    }

    @Override
    public Single<EventsResponse> getLikedEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.LIKED_EVENTS,EventsResponse.class);
        Single<EventsResponse> likedEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return likedEventsResponseSingle;
    }

    @Override
    public void saveLikedEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.LIKED_EVENTS,eventsResponse);
    }

    @Override
    public Single<VenuesResponse> getLikedVenues() {
        VenuesResponse eventsResponse = (VenuesResponse) sharedPref.getObject(sharedPref.LIKED_VENUES,EventsResponse.class);
        Single<VenuesResponse> likedEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return likedEventsResponseSingle;
    }

    @Override
    public void saveLikedVenues(VenuesResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.LIKED_VENUES,eventsResponse);
    }

    @Override
    public Single<VenuesResponse> getLikedAttractions() {
        VenuesResponse eventsResponse = (VenuesResponse) sharedPref.getObject(sharedPref.LIKED_ATTRACTIONS,EventsResponse.class);
        Single<VenuesResponse> likedEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return likedEventsResponseSingle;
    }

    @Override
    public void saveLikedAttractions(VenuesResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.LIKED_ATTRACTIONS,eventsResponse);
    }

    @Override
    public void saveLoggedUser(User user) {
        sharedPref.saveObject(SharedPref.USER,user);
    }

    @Override
    public Single<User> getUserProfile() {
        User user = (User) sharedPref.getObject(SharedPref.USER,User.class);
        Single<User> userSingle = Single.create(e -> {
            if (user != null) {
                e.onSuccess(user);
            }else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return userSingle;
    }

    @Override
    public void saveToken(String token) {
        sharedPref.saveString(SharedPref.TOKEN,token);
    }

    @Override
    public String getToken() {
        return sharedPref.getString(SharedPref.TOKEN);
    }

    @Override
    public void clearToken() {
        sharedPref.clearToken();
    }

    @Override
    public Single<ProfileResponse> getProfile() {
        ProfileResponse profileResponse = (ProfileResponse) sharedPref.getObject(SharedPref.PROFILE, ProfileResponse.class);
        Single<ProfileResponse> profileResponseSingle = Single.create(e -> {
            if (profileResponse != null) {
                e.onSuccess(profileResponse);
            } else {
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return profileResponseSingle;
    }

    @Override
    public void saveProfile(ProfileResponse profileResponse) {
        sharedPref.saveObject(SharedPref.PROFILE,profileResponse);
    }

    @Override
    public void clearProfile() {
        sharedPref.clearProfile();
    }
}
