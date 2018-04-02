package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ehab on 3/1/18.
 */

public interface AllAttractionsService {

    @GET("{lang}/attractions")
    Call<AllVenuesResponse> getAllAttractions(@Path("lang") String lang, @Query("page") int pageNumber, @Header("Authorization") String token);
}
