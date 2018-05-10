package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CancelAttractionService {
    @GET("{lang}/attraction/order/cancel/{id}")
    Call<ContactUsResponse> cancelAttraction(@Path("lang") String lang, @Header("Authorization") String token, @Path("id") int id);

}
