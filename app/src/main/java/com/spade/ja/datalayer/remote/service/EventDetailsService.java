package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/20/17.
 */

public interface EventDetailsService {
    @GET("{lang}/events/{id}")
    Call<EventInnerResponse> getEventDetails(@Path("lang") String lang, @Path("id") int id, @Header("Authorization") String token);
}
