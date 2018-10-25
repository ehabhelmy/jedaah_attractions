package com.spade.ja.datalayer.pojo.request.changeorder;

import com.google.gson.annotations.SerializedName;

public class ChangeOrderRequest {

    public static final String APPROVED = "1";
    public static final String REJECTED = "2";
    @SerializedName("order_id")
    private String orderId;

    @SerializedName("status")
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public ChangeOrderRequest(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
