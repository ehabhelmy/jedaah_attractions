package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/13/17.
 */

public interface NearByEventsService {
    @POST("{lang}/events/nearby/list")
    Call<EventsResponse> getNearbyEvents(@Body NearbyEventsRequest nearbyEventsRequest, @Path("lang") String lang);
}
