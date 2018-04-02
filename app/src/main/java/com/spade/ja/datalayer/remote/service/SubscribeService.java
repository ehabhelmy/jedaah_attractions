package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.subscribe.SubscribeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/20/18.
 */

public interface SubscribeService {

    @GET("{lang}/users/subscribe")
    Call<SubscribeResponse> subscribe(@Path("lang") String lang, @Header("Authorization") String token);
}
