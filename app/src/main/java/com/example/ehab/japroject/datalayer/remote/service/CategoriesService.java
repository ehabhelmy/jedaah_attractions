package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.category.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Romisaa.Attia on 12/12/2017.
 */

public interface CategoriesService {
    @GET("categories")
    Call<List<Category>> getCategories();
}
