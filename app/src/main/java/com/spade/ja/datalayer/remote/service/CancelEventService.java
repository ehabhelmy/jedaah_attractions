package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/29/18.
 */

public interface CancelEventService {
    @GET("{lang}/events/order/cancel/{id}")
    Call<ContactUsResponse> cancelEvent(@Path("lang") String lang, @Header("Authorization") String token, @Path("id") int id);

}
