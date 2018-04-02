package com.spade.ja.datalayer.pojo.request.contactus;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ehab on 3/12/18.
 */

public class ContactUsRequest {

    @SerializedName("subject")
    String subject;

    @SerializedName("message")
    String message;

    public ContactUsRequest(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
