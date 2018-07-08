package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.search.SearchRequest;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/20/18.
 */

public interface SearchService {

    @POST("{lang}/search/filter")
    Call<AllNearByResponse> search(@Path("lang") String lang, @Body SearchRequest searchRequest);
}
