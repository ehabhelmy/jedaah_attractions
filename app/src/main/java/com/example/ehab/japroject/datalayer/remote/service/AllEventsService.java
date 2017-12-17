package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/16/17.
 */

public interface AllEventsService {
    @GET("{lang}/events")
    Call<EventsResponse> getAllEvents(@Path("lang") String lang);
}
