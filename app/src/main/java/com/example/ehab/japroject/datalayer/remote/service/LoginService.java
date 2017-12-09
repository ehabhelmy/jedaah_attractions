package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.request.LoginRequest;
import com.example.ehab.japroject.datalayer.pojo.response.UserResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ehab on 12/2/17.
 */

public interface LoginService {
    @POST("login")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

//    @POST("login")
//    Single<UserResponse> loginUser(@Body LoginRequest loginRequest);
}
