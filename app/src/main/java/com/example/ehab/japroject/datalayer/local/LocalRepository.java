package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.example.ehab.japroject.util.Constants;

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
    public Single<EventsResponse> getAllEvents() {
        EventsResponse eventsResponse = (EventsResponse) sharedPref.getObject(sharedPref.ALL_EVENTS,EventsResponse.class);
        Single<EventsResponse> allEventsResponseSingle = Single.create(e -> {
            if (eventsResponse != null){
                e.onSuccess(eventsResponse);
            }else{
                e.onError(new Throwable(Constants.ERROR_NOT_CACHED));
            }
        });
        return allEventsResponseSingle;
    }

    @Override
    public void saveAllEvents(EventsResponse eventsResponse) {
        sharedPref.saveObject(SharedPref.ALL_EVENTS,eventsResponse);
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
        sharedPref.saveObject(SharedPref.TOKEN,token);
    }

    @Override
    public String getToken() {
        return sharedPref.getString(SharedPref.TOKEN);
    }
}
