package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.request.NearbyEventsRequest;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ehab on 12/13/17.
 */

public interface NearByEventsService {
    @POST("nearbyEvents")
    Call<EventsResponse> getNearbyEvents(@Body NearbyEventsRequest nearbyEventsRequest);
}
