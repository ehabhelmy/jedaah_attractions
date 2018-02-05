
package com.spade.ja.datalayer.pojo.response.venuesinner;

import java.util.List;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data extends BaseModel {

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
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("social_media")
    @Expose
    private List<SocialMedium> socialMedia = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;
    @SerializedName("is_sponsored")
    @Expose
    private Integer isSponsored;
    @SerializedName("is_brand")
    @Expose
    private Integer isBrand;
    @SerializedName("number_of_likes")
    @Expose
    private Integer numberOfLikes;
    @SerializedName("telephone_numbers")
    @Expose
    private String telephoneNumbers;
    @SerializedName("venue_opening_hours")
    @Expose
    private List<VenueOpeningHour> venueOpeningHours = null;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Integer getIsBrand() {
        return isBrand;
    }

    public void setIsBrand(Integer isBrand) {
        this.isBrand = isBrand;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(String telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public List<VenueOpeningHour> getVenueOpeningHours() {
        return venueOpeningHours;
    }

    public void setVenueOpeningHours(List<VenueOpeningHour> venueOpeningHours) {
        this.venueOpeningHours = venueOpeningHours;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

}
