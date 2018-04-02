package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/2/18.
 */

public interface LikedVenuesService {
    @GET("{lang}/venues/liked/list")
    Call<VenuesResponse> getLikedVenues(@Path("lang") String lang, @Header("Authorization") String token);
}
