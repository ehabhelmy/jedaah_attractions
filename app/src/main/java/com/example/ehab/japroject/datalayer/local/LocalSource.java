package com.example.ehab.japroject.datalayer.local;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;

import java.util.List;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;

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

    Single<List<Category>> getCategories();

    void saveCategories(List<Category> categoriesResponse);

    void saveTodayEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getTodayEvents();

    void saveWeekEvents(EventsResponse eventsResponse);

    Single<EventsResponse> getWeekEvents();

    Single<EventsResponse> getAllEvents();

    void saveAllEvents(EventsResponse eventsResponse);
}
