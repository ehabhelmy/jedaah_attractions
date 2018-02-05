package com.spade.ja.datalayer.pojo.response.sms;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ehab on 1/25/18.
 */

public class SMSResponse extends BaseModel {

    @SerializedName("MSG_ID")
    @Expose
    private int msgID;

    @SerializedName("Total")
    @Expose
    private double total;

    @SerializedName("Cost")
    @Expose
    private double cost;

    @SerializedName("STATUS")
    @Expose
    private boolean status;

    public int getMsgID() {
        return msgID;
    }

    public void setMsgID(int msgID) {
        this.msgID = msgID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
