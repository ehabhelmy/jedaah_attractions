package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Roma on 1/19/2018.
 */

public interface AllVenuesService {
    @GET("{lang}/venues")
    Call<VenuesResponse> getAllVenues(@Path("lang") String lang, @Header("Authorization") String token);
}
