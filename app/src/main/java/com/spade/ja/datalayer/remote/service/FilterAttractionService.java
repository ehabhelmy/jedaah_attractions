package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.filter.filtervenues.FilterVenuesRequest;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FilterAttractionService {

    @POST("{lang}/filter/attraction")
    Call<FilterVenuesResponse> filterAttractions(@Path("lang") String lang, @Body FilterVenuesRequest filterVenuesRequest);

}
