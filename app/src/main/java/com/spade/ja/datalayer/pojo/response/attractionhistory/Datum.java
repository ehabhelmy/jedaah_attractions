
package com.spade.ja.datalayer.pojo.response.attractionhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.attractioninner.Addon;

import java.util.List;

public class Datum extends BaseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("attraction")
    @Expose
    private Attraction attraction;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("order_number")
    @Expose
    private Integer orderNumber;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("tickets")
    private List<Ticket> tickets;
    @SerializedName("addons")
    private List<Addon> addons;

    public List<Addon> getAddons() {
        return addons;
    }

    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
