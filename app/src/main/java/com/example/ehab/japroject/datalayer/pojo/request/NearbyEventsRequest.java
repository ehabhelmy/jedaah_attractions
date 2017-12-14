package com.example.ehab.japroject.datalayer.pojo.request;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/13/17.
 */

public class NearbyEventsRequest extends BaseModel {

    private String lat;
    private String lng;

    public NearbyEventsRequest(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
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
