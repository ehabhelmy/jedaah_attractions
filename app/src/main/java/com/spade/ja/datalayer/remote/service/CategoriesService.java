package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.category.Category;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Romisaa.Attia on 12/12/2017.
 */

public interface CategoriesService {
    @GET("{lang}/categories")
    Call<Category> getCategories(@Path("lang") String lang);
}
