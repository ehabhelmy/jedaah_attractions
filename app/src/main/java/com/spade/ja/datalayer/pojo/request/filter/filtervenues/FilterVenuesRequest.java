package com.spade.ja.datalayer.pojo.request.filter.filtervenues;

import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;

import java.util.List;

/**
 * Created by ehab on 2/23/18.
 */

public class FilterVenuesRequest extends BaseModel {

    @SerializedName("week_suggest")
    private boolean weeklySuggest;

    @SerializedName("category")
    private List<Integer> categoryId;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public FilterVenuesRequest(boolean weeklySuggest, List<Integer> categoryId, Double lat, Double lng) {
        this.weeklySuggest = weeklySuggest;
        this.categoryId = categoryId;
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public boolean isWeeklySuggest() {
        return weeklySuggest;
    }

    public void setWeeklySuggest(boolean weeklySuggest) {
        this.weeklySuggest = weeklySuggest;
    }

    public List<Integer> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }
}
