package com.example.ehab.japroject.datalayer.pojo.request;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/2/17.
 */

public class LoginRequest extends BaseModel {

    private String email;

    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
