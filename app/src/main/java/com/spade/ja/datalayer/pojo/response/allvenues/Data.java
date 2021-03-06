package com.spade.ja.datalayer.pojo.response.allvenues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Roma on 1/19/2018.
 */

public class Data {

    @SerializedName("venues")
    @Expose
    private List<Venue> venues;
    @SerializedName("attractions")
    @Expose
    private List<Venue> attractions;
    @SerializedName("first_page_url")
    @Expose
    private String firstPageUrl;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;
    @SerializedName("next_page_url")
    @Expose
    private String nextPageUrl;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("prev_page_url")
    @Expose
    private String prevPageUrl;
    @SerializedName("last_page_url")
    @Expose
    private String lastPageUrl;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Venue> attractions) {
        this.attractions = attractions;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
