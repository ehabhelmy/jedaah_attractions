package com.spade.ja.ui.Home.map;


import com.spade.ja.datalayer.pojo.BaseModel;

/**
 * Created by Roma on 2/7/2018.
 */

public class Data extends BaseModel {

    private String title;
    private String image;
    private String description;
    private String lat;
    private String lng;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
