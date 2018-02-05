package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.response.sms.SMSResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ehab on 1/25/18.
 */

public interface SMSService {

    @GET("sendsms")
    Call<SMSResponse> sendSMS(@Query("user") String userName,@Query("pass") String password,@Query("to") String phone,@Query("message") String message,@Query("sender") String sender);
}
