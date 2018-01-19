package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 1/18/18.
 */

public interface NearyByVenuesService {
    @POST("{lang}/venues/nearby/list")
    Call<VenuesResponse> getNearbyVenues(@Body NearbyEventsRequest nearbyEventsRequest, @Path("lang") String lang, @Header("Authorization") String token);
}
