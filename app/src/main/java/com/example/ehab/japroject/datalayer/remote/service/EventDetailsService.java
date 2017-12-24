package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/20/17.
 */

public interface EventDetailsService {
    @GET("{lang}/events/{id}")
    Call<EventInnerResponse> getEventDetails(@Path("lang") String lang, @Path("id") int id);
}
