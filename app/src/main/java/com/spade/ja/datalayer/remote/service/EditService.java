package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.login.LoginResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by ehab on 2/3/18.
 */

public interface EditService {
        @Multipart
        @POST("{lang}/profile/edit")
        Call<LoginResponse> edit(@Path("lang") String lang,
                                 @Header("Authorization") String token,
                                 @Part("name") RequestBody name,
                                 @Part("email") RequestBody email,
                                 @Part("password") RequestBody password,
                                 @Part("mobile_number") RequestBody mobile_number,
                                 @Part("birth_date") RequestBody birthDate,
                                 @Part("gender") RequestBody gender,
                                 @Part MultipartBody.Part profile_image);
}
