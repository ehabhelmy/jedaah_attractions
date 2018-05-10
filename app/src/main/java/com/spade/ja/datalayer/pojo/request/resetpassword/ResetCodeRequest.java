package com.spade.ja.datalayer.pojo.request.resetpassword;

import com.google.gson.annotations.SerializedName;

public class ResetCodeRequest {

    @SerializedName("email")
    private String email;

    public ResetCodeRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
