package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ehab on 12/16/17.
 */

public interface AllEventsService {
    @GET("{lang}/events")
    Call<AllEventsResponse> getAllEvents(@Path("lang") String lang, @Query("page") int pageNumber, @Header("Authorization") String token);
}
