package com.spade.ja.ui.Home.explore.pojo;

import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/12/17.
 */

public class Event extends BaseModel {

    private String eventImage;
    private String eventName;
    private String eventMonth;
    private String eventDay;
    private String eventRemaining;
    private String eventAddress;
    private String eventLikes;
    private int id;
    private boolean isLiked;

    public Event() {
    }

    public Event(int id ,String eventImage, String eventName, String eventMonth, String eventDay, String eventRemaining, String eventAddress, String eventLikes,boolean isLiked) {
        this.eventImage = eventImage;
        this.eventName = eventName;
        this.eventMonth = eventMonth;
        this.eventDay = eventDay;
        this.eventRemaining = eventRemaining;
        this.eventAddress = eventAddress;
        this.eventLikes = eventLikes;
        this.id = id;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getEventRemaining() {
        return eventRemaining;
    }

    public void setEventRemaining(String eventRemaining) {
        this.eventRemaining = eventRemaining;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventLikes() {
        return eventLikes;
    }

    public void setEventLikes(String eventLikes) {
        this.eventLikes = eventLikes;
    }
}
