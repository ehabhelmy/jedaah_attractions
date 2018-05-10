package com.spade.ja.ui.Home.profile.my_tickets.pojo;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryEventsUi extends BaseModel {

    private int id;
    private String eventTitle;
    private String eventImage;
    private String eventMonth;
    private String eventDay;
    private String eventTime;
    private String paymentMethod;
    private String ticketType;
    private String orderNumber;
    private String orderStatus;
    private String numberTickets;
    private String ticketsPrice;
    private String type;
    private String addons;
    private boolean upOrPast;

    public boolean isUpOrPast() {
        return upOrPast;
    }

    public void setUpOrPast(boolean upOrPast) {
        this.upOrPast = upOrPast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddons() {
        return addons;
    }

    public void setAddons(String addons) {
        this.addons = addons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventMonth() {
        return eventMonth;
    }

    public void setEventMonth(String eventMonth) {
        this.eventMonth = eventMonth;
    }

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(String numberTickets) {
        this.numberTickets = numberTickets;
    }

    public String getTicketsPrice() {
        return ticketsPrice;
    }

    public void setTicketsPrice(String ticketsPrice) {
        this.ticketsPrice = ticketsPrice;
    }
}
