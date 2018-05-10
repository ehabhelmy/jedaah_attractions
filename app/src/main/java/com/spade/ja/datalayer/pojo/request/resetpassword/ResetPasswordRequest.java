package com.spade.ja.datalayer.pojo.request.resetpassword;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @SerializedName("forget_code")
    private String code;

    @SerializedName("password")
    private String password;

    public ResetPasswordRequest(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
