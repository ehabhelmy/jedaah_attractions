package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ehab on 12/2/17.
 */

public interface RegisterService {

    @POST("register")
    Call<UserResponse> registerUser(@Body User user);

//    @POST("register")
//    Single<UserResponse> registerUser(@Body User user);
}
