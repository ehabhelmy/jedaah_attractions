package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.attractionhistory.AttractionOrderHistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AttractionOrderUpcomingService {
    @GET("{lang}/history/attractions/upcoming")
    Call<AttractionOrderHistoryResponse> getUpcomingAttractions(@Path("lang") String lang, @Header("Authorization") String token);

}
