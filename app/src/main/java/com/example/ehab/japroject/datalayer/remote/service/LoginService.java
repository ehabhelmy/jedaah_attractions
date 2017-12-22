package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.request.LoginRequest;
import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/21/17.
 */

public interface LoginService {
    @POST("{lang}/login")
    Call<LoginResponse> login(@Path("lang") String lang,@Body LoginRequest loginRequest);
}
