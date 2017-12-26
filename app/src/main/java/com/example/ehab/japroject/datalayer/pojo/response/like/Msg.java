package com.example.ehab.japroject.datalayer.pojo.response.like;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ehab on 12/25/17.
 */

public class Msg extends BaseModel {

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {return msg;}

    public void setMsg(String msg) {this.msg = msg;}
}
