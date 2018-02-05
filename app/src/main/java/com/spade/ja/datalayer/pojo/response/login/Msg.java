package com.spade.ja.datalayer.pojo.response.login;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ehab on 12/23/17.
 */

public class Msg extends BaseModel {

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("errors")
    @Expose
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
