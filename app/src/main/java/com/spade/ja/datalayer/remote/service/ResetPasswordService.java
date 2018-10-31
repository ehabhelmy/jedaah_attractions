package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.resetpassword.ResetPasswordRequest;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.usecase.contactus.ContactUs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResetPasswordService {
    @POST("{lang}/password/reset")
    Call<ContactUsResponse> resetPassword(@Path("lang") String lang, @Body ResetPasswordRequest resetPasswordRequest);

}
