
package com.spade.ja.datalayer.pojo.response.viewtickets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

public class Pivot extends BaseModel{

    @SerializedName("attraction_week_day_id")
    @Expose
    private Integer attractionWeekDayId;
    @SerializedName("attraction_ticket_id")
    @Expose
    private Integer attractionTicketId;

    public Integer getAttractionWeekDayId() {
        return attractionWeekDayId;
    }

    public void setAttractionWeekDayId(Integer attractionWeekDayId) {
        this.attractionWeekDayId = attractionWeekDayId;
    }

    public Integer getAttractionTicketId() {
        return attractionTicketId;
    }

    public void setAttractionTicketId(Integer attractionTicketId) {
        this.attractionTicketId = attractionTicketId;
    }

}
