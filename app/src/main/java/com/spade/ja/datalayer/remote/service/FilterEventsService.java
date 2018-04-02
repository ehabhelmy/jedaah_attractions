package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.LoginRequest;
import com.spade.ja.datalayer.pojo.request.filter.filterevents.FilterEventsRequest;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 2/23/18.
 */

public interface FilterEventsService {

    @POST("{lang}/filter/event")
    Call<FilterEventsResponse> filterEvents(@Path("lang") String lang, @Body FilterEventsRequest filterEventsRequest);
}
