package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.TopEventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ehab on 12/12/17.
 */

public interface TopEventsService {
    @GET("topEvents")
    Call<TopEventsResponse> getTopEvents();
}
