package com.spade.ja.datalayer.pojo.response.subscribe;

import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 3/20/18.
 */

public class Data extends BaseModel {

    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
