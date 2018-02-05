
package com.spade.ja.datalayer.pojo.response.allevents;

import java.util.List;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data extends BaseModel{

    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("next_page_url")
    @Expose
    private String nextPageUrl;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

}
