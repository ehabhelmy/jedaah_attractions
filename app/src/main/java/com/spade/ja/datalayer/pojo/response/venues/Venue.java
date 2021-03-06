
package com.spade.ja.datalayer.pojo.response.venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

import java.util.List;

public class Venue extends BaseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("sub_categories")
    @Expose
    private List<String> subCategories = null;
    @SerializedName("is_sponsored")
    @Expose
    private Integer isSponsored;
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;
    @SerializedName("is_brand")
    @Expose
    private Integer isBrand;
    @SerializedName("number_of_likes")
    @Expose
    private Integer numberOfLikes;
    @SerializedName("is_liked")
    @Expose
    private Boolean isLiked;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lng")
    @Expose
    private String lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIsSponsored() {
        return isSponsored;
    }

    public void setIsSponsored(Integer isSponsored) {
        this.isSponsored = isSponsored;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
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

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
