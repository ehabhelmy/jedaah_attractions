package com.example.ehab.japroject.datalayer.pojo.response.like;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ehab on 12/25/17.
 */

public class LikeResponse extends BaseModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data = null;
    @SerializedName("msg")
    @Expose
    private Msg msg = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Msg getMsg() {return msg;}

    public void setMsg(Msg msg) {this.msg = msg;}
}
