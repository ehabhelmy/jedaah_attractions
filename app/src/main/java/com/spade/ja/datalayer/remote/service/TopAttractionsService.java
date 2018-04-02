package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/1/18.
 */

public interface TopAttractionsService {
    @GET("{lang}/attractions/top/list")
    Call<VenuesResponse> getTopAttractions(@Path("lang") String lang, @Header("Authorization") String token);
}
