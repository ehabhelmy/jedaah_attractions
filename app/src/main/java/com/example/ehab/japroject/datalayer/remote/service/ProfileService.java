package com.example.ehab.japroject.datalayer.remote.service;

import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Romisaa on 12/26/2017.
 */

public interface ProfileService {

    @GET("{lang}/profile")
    Call<ProfileResponse> getProfile(@Path("lang") String lang, @Header("Authorization") String profileHeader);

}
