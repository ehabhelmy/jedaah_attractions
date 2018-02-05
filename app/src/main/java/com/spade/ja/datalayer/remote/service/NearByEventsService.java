package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.NearbyEventsRequest;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/13/17.
 */

public interface NearByEventsService {
    @POST("{lang}/events/nearby/list")
    Call<EventsResponse> getNearbyEvents(@Body NearbyEventsRequest nearbyEventsRequest, @Path("lang") String lang,@Header("Authorization") String token);
}
