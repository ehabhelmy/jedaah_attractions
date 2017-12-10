package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataService {

    @GET("/data")
    Call<DataResponse> getData();
}
