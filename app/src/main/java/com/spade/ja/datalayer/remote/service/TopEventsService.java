package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/12/17.
 */

public interface TopEventsService {
    @GET("{lang}/events/top/list")
    Call<EventsResponse> getTopEvents(@Path("lang") String lang,@Header("Authorization") String token);
}
