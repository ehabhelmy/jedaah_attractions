package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataService {

    @GET("/data")
    Call<DataResponse> getData(@Path("lang") String lang);
}
