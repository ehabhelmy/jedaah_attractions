
package com.spade.ja.datalayer.pojo.response.attractioninner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

import java.util.List;

public class Day extends BaseModel{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("attraction_week_days")
    @Expose
    private List<AttractionWeekDay> attractionWeekDays = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<AttractionWeekDay> getAttractionWeekDays() {
        return attractionWeekDays;
    }

    public void setAttractionWeekDays(List<AttractionWeekDay> attractionWeekDays) {
        this.attractionWeekDays = attractionWeekDays;
    }

}
