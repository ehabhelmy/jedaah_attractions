package com.example.ehab.japroject.datalayer.pojo.request;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/21/17.
 */

public class LoginRequest extends BaseModel {

    private String email;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest(String email, String facebook_id, String google_id) {
        this.email = email;
        this.facebook_id = facebook_id;
        this.google_id = google_id;
    }

    private String password;

    private String facebook_id;

    private String google_id;

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
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
