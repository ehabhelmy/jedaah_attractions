package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.contactus.ContactUsRequest;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ehab on 3/12/18.
 */

public interface ContactUsService {
    @POST("{lang}/contact")
    Call<ContactUsResponse> contactUs(@Path("lang") String lang, @Header("Authorization") String token, @Body ContactUsRequest contactUsRequest);
}
