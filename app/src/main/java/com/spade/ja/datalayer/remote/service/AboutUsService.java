package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.about.AboutUsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AboutUsService {

    @GET("{lang}/about-us")
    Call<AboutUsResponse> about(@Path("lang") String lang);

}
