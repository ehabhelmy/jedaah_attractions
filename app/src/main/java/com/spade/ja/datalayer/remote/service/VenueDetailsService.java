package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.venuesinner.VenuesInnerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ehab on 1/23/18.
 */

public interface VenueDetailsService {

    @GET("{lang}/venues/{id}")
    Call<VenuesInnerResponse> getVenueDetails(@Path("lang") String lang, @Path("id") int id);
}
