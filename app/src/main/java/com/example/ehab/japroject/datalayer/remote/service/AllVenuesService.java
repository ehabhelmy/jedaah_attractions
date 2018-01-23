package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.allvenues.AllVenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Roma on 1/19/2018.
 */

public interface AllVenuesService {
    @GET("{lang}/venues")
    Call<AllVenuesResponse> getAllVenues(@Path("lang") String lang, @Query("page") int pageNumber, @Header("Authorization") String token);
}
