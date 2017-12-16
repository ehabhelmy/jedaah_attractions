package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ehab on 12/16/17.
 */

public interface AllEventsService {
    @GET("getEvents")
    Call<EventsResponse> getAllEvents();
}
