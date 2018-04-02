package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/30/17.
 */

public interface PastEventsService {
    @GET("{lang}/history/orders/past")
    Call<HistoryEvents> getPastEvents(@Path("lang") String lang, @Header("Authorization") String token);
}
