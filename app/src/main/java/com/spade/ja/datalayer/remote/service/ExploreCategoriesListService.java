package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.filter.filtercategories.FilterCategoriesRequest;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExploreCategoriesListService {

    @POST("{lang}/filter/category/all")
    Call<AllNearByResponse> filterEvents(@Path("lang") String lang, @Body FilterCategoriesRequest filterEventsRequest);
}
