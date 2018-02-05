package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 1/18/18.
 */

public interface TopVenuesService {
    @GET("{lang}/venues/top/list")
    Call<VenuesResponse> getTopVenues(@Path("lang") String lang, @Header("Authorization") String token);
}
