package com.spade.ja.datalayer.pojo.request.attractionorder;

import com.spade.ja.datalayer.pojo.BaseModel;

import java.util.List;

/**
 * Created by ehab on 3/18/18.
 */

public class AttractionOrderRequest extends BaseModel {

    private String name;
    private String email;
    private String mobile_number;
    private String payment_method;
    private int attraction_id;
    private int total;
    private Integer exceptional_date_id;
    private Integer attraction_week_day_id;
    private List<Ticket> tickets;

    public AttractionOrderRequest() {
    }

    public AttractionOrderRequest(String name, String email, String mobile_number, String payment_method, int attraction_id, int total, Integer exceptional_date_id, Integer attraction_week_day_id, List<Ticket> tickets) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.payment_method = payment_method;
        this.attraction_id = attraction_id;
        this.total = total;
        this.exceptional_date_id = exceptional_date_id;
        this.attraction_week_day_id = attraction_week_day_id;
        this.tickets = tickets;
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

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getExceptional_date_id() {
        return exceptional_date_id;
    }

    public void setExceptional_date_id(Integer exceptional_date_id) {
        this.exceptional_date_id = exceptional_date_id;
    }

    public Integer getAttraction_week_day_id() {
        return attraction_week_day_id;
    }

    public void setAttraction_week_day_id(Integer attraction_week_day_id) {
        this.attraction_week_day_id = attraction_week_day_id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
