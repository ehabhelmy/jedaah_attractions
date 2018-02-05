package com.spade.ja.datalayer.pojo.request.order;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/28/17.
 */

public class OrderRequest extends BaseModel {

    private String name;
    private String email;
    private String mobile_number;
    private String number_of_tickets;
    private String payment_method;
    private String event_id;
    private String event_ticket_id;
    private String ticket_date_id;
    private String user_national_id;
    private String total;

    public OrderRequest(String name, String email, String mobile_number, String number_of_tickets, String payment_method, String event_id, String event_ticket_id, String ticket_date_id, String national_id, String total) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.number_of_tickets = number_of_tickets;
        this.payment_method = payment_method;
        this.event_id = event_id;
        this.event_ticket_id = event_ticket_id;
        this.ticket_date_id = ticket_date_id;
        this.user_national_id = national_id;
        this.total = total;
    }

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

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(String number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_ticket_id() {
        return event_ticket_id;
    }

    public void setEvent_ticket_id(String event_ticket_id) {
        this.event_ticket_id = event_ticket_id;
    }

    public String getTicket_date_id() {
        return ticket_date_id;
    }

    public void setTicket_date_id(String ticket_date_id) {
        this.ticket_date_id = ticket_date_id;
    }

    public String getNational_id() {
        return user_national_id;
    }

    public void setNational_id(String national_id) {
        this.user_national_id = national_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
