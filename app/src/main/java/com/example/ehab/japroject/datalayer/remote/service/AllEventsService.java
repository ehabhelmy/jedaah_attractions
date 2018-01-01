package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.allevents.AllEventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ehab on 12/16/17.
 */

public interface AllEventsService {
    @GET("{lang}/events")
    Call<AllEventsResponse> getAllEvents(@Path("lang") String lang, @Query("page") int pageNumber);
}
