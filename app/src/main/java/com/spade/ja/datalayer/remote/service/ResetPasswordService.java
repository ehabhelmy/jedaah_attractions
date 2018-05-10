package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.resetpassword.ResetPasswordRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResetPasswordService {
    @POST("{lang}/password/reset")
    Call<ContactUsService> resetPassword(@Path("lang") String lang, @Body ResetPasswordRequest resetPasswordRequest);

}
