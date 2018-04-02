package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/9/18.
 */

public interface AttractionInnerService {
    @GET("{lang}/attractions/{id}")
    Call<AttractionInnerResponse> getAttractionsDetails(@Path("lang") String lang, @Path("id") int id, @Header("Authorization") String token);

}
