
package com.example.ehab.japroject.datalayer.pojo.response.venuesinner;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueOpeningHour extends BaseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("venue_day")
    @Expose
    private String venueDay;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("is_closed")
    @Expose
    private Integer isClosed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVenueDay() {
        return venueDay;
    }

    public void setVenueDay(String venueDay) {
        this.venueDay = venueDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

}
