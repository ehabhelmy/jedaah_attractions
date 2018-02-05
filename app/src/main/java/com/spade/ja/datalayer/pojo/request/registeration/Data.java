package com.spade.ja.datalayer.pojo.request.registeration;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Romisaa on 12/23/2017.
 */

public class Data extends BaseModel {

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
