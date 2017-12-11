
package com.example.ehab.japroject.datalayer.pojo.response;

import java.util.List;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum extends BaseModel{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("sub-category")
    @Expose
    private SubCategory subCategory;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("Event Date and Time")
    @Expose
    private String eventDateAndTime;
    @SerializedName("social media")
    @Expose
    private List<SocialMedium> socialMedia = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Price")
    @Expose
    private String price;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(String eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    public List<SocialMedium> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedium> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
