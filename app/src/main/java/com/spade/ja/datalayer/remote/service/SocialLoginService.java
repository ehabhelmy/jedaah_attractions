package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.LoginRequest;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/25/17.
 */

public interface SocialLoginService {

    @POST("{lang}/login/social")
    Call<LoginResponse> socialLogin(@Path("lang") String lang, @Body LoginRequest loginRequest);
}
