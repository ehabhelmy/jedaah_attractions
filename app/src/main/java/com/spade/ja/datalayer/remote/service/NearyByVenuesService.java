package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.NearbyEventsRequest;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;

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
