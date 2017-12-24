package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.pojo;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;

import java.util.ArrayList;

/**
 * Created by ehab on 12/20/17.
 */

public class EventDetails extends BaseModel {

    private String eventTitle;
    private String categoriesText;
    private String interested;
    private String eventMonth;
    private String eventDay;
    private String eventDatRemaining;
    private boolean isliked;
    private String eventAddress;
    private String eventDatetitle;
    private ArrayList<String> eventDateDays;
    private ArrayList<SocialMedium> socialMedia;
    private ArrayList<EventTag> eventTags;
    private String eventPrice;
    private String eventDescription;
    private Double latitude;
    private Double longitude;
    private ArrayList<String> imageURLS;

    public ArrayList<String> getImageURLS() {
        return imageURLS;
    }

    public void setImageURLS(ArrayList<String> imageURLS) {
        this.imageURLS = imageURLS;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getCategoriesText() {
        return categoriesText;
    }

    public void setCategoriesText(String categoriesText) {
        this.categoriesText = categoriesText;
    }

    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
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

    public String getEventDatRemaining() {
        return eventDatRemaining;
    }

    public void setEventDatRemaining(String eventDatRemaining) {
        this.eventDatRemaining = eventDatRemaining;
    }

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventDatetitle() {
        return eventDatetitle;
    }

    public void setEventDatetitle(String eventDatetitle) {
        this.eventDatetitle = eventDatetitle;
    }

    public ArrayList<String> getEventDateDays() {
        return eventDateDays;
    }

    public void setEventDateDays(ArrayList<String> eventDateDays) {
        this.eventDateDays = eventDateDays;
    }

    public ArrayList<SocialMedium> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(ArrayList<SocialMedium> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public ArrayList<EventTag> getEventTags() {
        return eventTags;
    }

    public void setEventTags(ArrayList<EventTag> eventTags) {
        this.eventTags = eventTags;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
