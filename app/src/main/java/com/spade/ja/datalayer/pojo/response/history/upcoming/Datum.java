
package com.spade.ja.datalayer.pojo.response.history.upcoming;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum extends BaseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("event")
    @Expose
    private Event event;
    @SerializedName("event_ticket")
    @Expose
    private EventTicket eventTicket;
    @SerializedName("ticket_date")
    @Expose
    private TicketDate ticketDate;
    @SerializedName("number_of_tickets")
    @Expose
    private Integer numberOfTickets;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("order_number")
    @Expose
    private Integer orderNumber;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventTicket getEventTicket() {
        return eventTicket;
    }

    public void setEventTicket(EventTicket eventTicket) {
        this.eventTicket = eventTicket;
    }

    public TicketDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(TicketDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
