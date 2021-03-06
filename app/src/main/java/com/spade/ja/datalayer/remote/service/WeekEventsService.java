package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Romisaa on 12/16/2017.
 */

public interface WeekEventsService {
    @GET("{lang}/events/thisWeek/list")
    Call<EventsResponse> getWeekEvents(@Path("lang") String lang,@Header("Authorization") String token);
}
