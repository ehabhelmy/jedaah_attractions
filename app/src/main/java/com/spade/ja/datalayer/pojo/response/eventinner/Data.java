
package com.spade.ja.datalayer.pojo.response.eventinner;

import java.util.List;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data extends BaseModel{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("gallery")
    @Expose
    private List<String> gallery = null;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("sub_categories")
    @Expose
    private List<String> subCategories = null;
    @SerializedName("address_url")
    @Expose
    private String addressUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("event_days")
    @Expose
    private List<EventDay> eventDays = null;
    @SerializedName("social_media")
    @Expose
    private List<SocialMedium> socialMedia = null;
    @SerializedName("event_tags")
    @Expose
    private List<EventTag> eventTags = null;
    @SerializedName("event_tickets")
    @Expose
    private List<EventTicket> eventTickets = null;
    @SerializedName("ticket_dates")
    @Expose
    private List<TicketDate> ticketDates = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_price")
    @Expose
    private String startPrice;
    @SerializedName("end_price")
    @Expose
    private String endPrice;
    @SerializedName("credit_card")
    @Expose
    private Integer creditCard;
    @SerializedName("cash_on_delivery")
    @Expose
    private Integer cashOnDelivery;
    @SerializedName("pay_later")
    @Expose
    private Integer payLater;
    @SerializedName("max_of_pay_later_tickets")
    @Expose
    private Integer maxOfPayLaterTickets;
    @SerializedName("max_of_cash_tickets")
    @Expose
    private Integer maxOfCashTickets;
    @SerializedName("national_id")
    @Expose
    private Integer nationalId;
    @SerializedName("interested")
    @Expose
    private Integer interested;
    @SerializedName("going")
    @Expose
    private Integer going;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("is_liked")
    @Expose
    private Boolean isLiked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EventDay> getEventDays() {
        return eventDays;
    }

    public void setEventDays(List<EventDay> eventDays) {
        this.eventDays = eventDays;
    }

    public List<SocialMedium> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedium> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<EventTag> getEventTags() {
        return eventTags;
    }

    public void setEventTags(List<EventTag> eventTags) {
        this.eventTags = eventTags;
    }

    public List<EventTicket> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<EventTicket> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public List<TicketDate> getTicketDates() {
        return ticketDates;
    }

    public void setTicketDates(List<TicketDate> ticketDates) {
        this.ticketDates = ticketDates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Integer creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Integer cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public Integer getPayLater() {
        return payLater;
    }

    public void setPayLater(Integer payLater) {
        this.payLater = payLater;
    }

    public Integer getMaxOfPayLaterTickets() {
        return maxOfPayLaterTickets;
    }

    public void setMaxOfPayLaterTickets(Integer maxOfPayLaterTickets) {
        this.maxOfPayLaterTickets = maxOfPayLaterTickets;
    }

    public Integer getMaxOfCashTickets() {
        return maxOfCashTickets;
    }

    public void setMaxOfCashTickets(Integer maxOfCashTickets) {
        this.maxOfCashTickets = maxOfCashTickets;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public Integer getInterested() {
        return interested;
    }

    public void setInterested(Integer interested) {
        this.interested = interested;
    }

    public Integer getGoing() {
        return going;
    }

    public void setGoing(Integer going) {
        this.going = going;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

}
