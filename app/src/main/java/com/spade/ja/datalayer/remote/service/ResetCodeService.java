package com.spade.ja.datalayer.remote.service;

import com.spade.ja.datalayer.pojo.request.resetpassword.ResetCodeRequest;
import com.spade.ja.datalayer.pojo.response.code.ResetCodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResetCodeService {
    @POST("{lang}/password/code")
    Call<ResetCodeResponse> getCode(@Path("lang") String lang,@Body ResetCodeRequest resetCodeRequest);

}
