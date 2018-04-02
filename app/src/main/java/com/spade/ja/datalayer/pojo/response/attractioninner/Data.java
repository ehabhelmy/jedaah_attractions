
package com.spade.ja.datalayer.pojo.response.attractioninner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.eventinner.EventTag;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;

import java.util.List;

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
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("sub_categories")
    @Expose
    private List<String> subCategories = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_url")
    @Expose
    private String addressUrl;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("attraction_tags")
    @Expose
    private List<EventTag> attractionTags = null;
    @SerializedName("social_media")
    @Expose
    private List<SocialMedium> socialMedia = null;
    @SerializedName("attraction_tickets")
    @Expose
    private List<String> attractionTickets = null;
    @SerializedName("attraction_addons")
    @Expose
    private List<String> attractionAddons = null;
    @SerializedName("attraction_exceptional_dates")
    @Expose
    private List<AttractionExceptionalDate> attractionExceptionalDates = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;
    @SerializedName("is_sponsored")
    @Expose
    private Integer isSponsored;
    @SerializedName("week_suggest")
    @Expose
    private Integer weekSuggest;
    @SerializedName("number_of_days")
    @Expose
    private Integer numberOfDays;
    @SerializedName("cash_on_delivery")
    @Expose
    private Integer cashOnDelivery;
    @SerializedName("credit_card")
    @Expose
    private Integer creditCard;
    @SerializedName("pay_later")
    @Expose
    private Integer payLater;
    @SerializedName("max_of_pay_later_tickets")
    @Expose
    private Integer maxOfPayLaterTickets;
    @SerializedName("max_of_cash_tickets")
    @Expose
    private Integer maxOfCashTickets;
    @SerializedName("number_of_likes")
    @Expose
    private Integer numberOfLikes;
    @SerializedName("number_of_going")
    @Expose
    private Integer numberOfGoing;
    @SerializedName("contact_numbers")
    @Expose
    private String contactNumbers;
    @SerializedName("is_liked")
    @Expose
    private Boolean isLiked;
    @SerializedName("days")
    @Expose
    private List<Day> days = null;

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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
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

    public List<EventTag> getAttractionTags() {
        return attractionTags;
    }

    public void setAttractionTags(List<EventTag> attractionTags) {
        this.attractionTags = attractionTags;
    }

    public List<SocialMedium> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedium> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<String> getAttractionTickets() {
        return attractionTickets;
    }

    public void setAttractionTickets(List<String> attractionTickets) {
        this.attractionTickets = attractionTickets;
    }

    public List<String> getAttractionAddons() {
        return attractionAddons;
    }

    public void setAttractionAddons(List<String> attractionAddons) {
        this.attractionAddons = attractionAddons;
    }

    public List<AttractionExceptionalDate> getAttractionExceptionalDates() {
        return attractionExceptionalDates;
    }

    public void setAttractionExceptionalDates(List<AttractionExceptionalDate> attractionExceptionalDates) {
        this.attractionExceptionalDates = attractionExceptionalDates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getIsSponsored() {
        return isSponsored;
    }

    public void setIsSponsored(Integer isSponsored) {
        this.isSponsored = isSponsored;
    }

    public Integer getWeekSuggest() {
        return weekSuggest;
    }

    public void setWeekSuggest(Integer weekSuggest) {
        this.weekSuggest = weekSuggest;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Integer cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public Integer getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Integer creditCard) {
        this.creditCard = creditCard;
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

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfGoing() {
        return numberOfGoing;
    }

    public void setNumberOfGoing(Integer numberOfGoing) {
        this.numberOfGoing = numberOfGoing;
    }

    public String getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(String contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

}
