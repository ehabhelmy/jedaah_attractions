package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Romisaa on 12/16/2017.
 */

public interface WeekEventsService {
    @GET("thisWeekEvents")
    Call<EventsResponse> getWeekEvents();
}
