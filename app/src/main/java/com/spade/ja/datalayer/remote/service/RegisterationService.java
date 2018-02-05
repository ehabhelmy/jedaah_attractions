package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.login.LoginResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Romisaa on 12/22/2017.
 */

public interface RegisterationService {
    @Multipart
    @POST("{lang}/register")
    Call<LoginResponse> register(@Path("lang") String lang,
                                 @Part("name") RequestBody name,
                                 @Part("username") RequestBody username,
                                 @Part("email") RequestBody email,
                                 @Part("password") RequestBody password,
                                 @Part("mobile_number") RequestBody mobile_number,
                                 @Part MultipartBody.Part profile_image);
}