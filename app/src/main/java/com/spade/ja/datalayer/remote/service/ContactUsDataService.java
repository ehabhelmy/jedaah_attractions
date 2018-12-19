package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.contactus.ContactUsRequest;
import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContactUsDataService {
    @GET("{lang}/contact/data")
    Call<ContactUsDataResponse> contactUs(@Path("lang") String lang);

}
