package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.like.LikeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 12/25/17.
 */

public interface LikeService {

    @GET("{lang}/events/{id}/like")
    Call<LikeResponse> like(@Path("lang") String lang, @Path("id") int id, @Header("Authorization") String token);
}
