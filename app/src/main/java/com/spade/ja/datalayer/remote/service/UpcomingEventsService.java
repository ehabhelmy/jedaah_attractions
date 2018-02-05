package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/30/17.
 */

public interface UpcomingEventsService {
    @GET("{lang}/history/orders/upcoming")
    Call<HistoryEvents> getUpcomingEvents(@Path("lang") String lang, @Header("Authorization") String token);
}
