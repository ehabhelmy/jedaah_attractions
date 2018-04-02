package com.spade.ja.datalayer.pojo.request.attractionorder;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 3/18/18.
 */

public class Ticket extends BaseModel {

    private Integer attraction_ticket_id;
    private Integer attraction_addon_id;
    private Integer number;

    public Ticket(Integer attraction_ticket_id, Integer attraction_addon_id, Integer number) {
        this.attraction_ticket_id = attraction_ticket_id;
        this.attraction_addon_id = attraction_addon_id;
        this.number = number;
    }

    public Integer getAttraction_ticket_id() {
        return attraction_ticket_id;
    }

    public void setAttraction_ticket_id(Integer attraction_ticket_id) {
        this.attraction_ticket_id = attraction_ticket_id;
    }

    public Integer getAttraction_addon_id() {
        return attraction_addon_id;
    }

    public void setAttraction_addon_id(Integer attraction_addon_id) {
        this.attraction_addon_id = attraction_addon_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
