package com.spade.ja.datalayer.pojo.response.attractionhistory;

import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

public class Ticket extends BaseModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("type")
    private String type;

    @SerializedName("number_of_tickets")
    private Integer numberOfTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
