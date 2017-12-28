
package com.example.ehab.japroject.datalayer.pojo.response.order;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order extends BaseModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("number_of_tickets")
    @Expose
    private String numberOfTickets;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_ticket_id")
    @Expose
    private String eventTicketId;
    @SerializedName("ticket_date_id")
    @Expose
    private String ticketDateId;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("order_number")
    @Expose
    private Integer orderNumber;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(String numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTicketId() {
        return eventTicketId;
    }

    public void setEventTicketId(String eventTicketId) {
        this.eventTicketId = eventTicketId;
    }

    public String getTicketDateId() {
        return ticketDateId;
    }

    public void setTicketDateId(String ticketDateId) {
        this.ticketDateId = ticketDateId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
