package com.example.ehab.japroject.datalayer;

import com.example.ehab.japroject.datalayer.pojo.response.DataResponse;
import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Single;

/**
 * Created by ehab on 12/2/17.
 */

public interface DataSource {

    Single<DataResponse> getData();

    Single<EventsResponse> getTopEvents();

    Single<EventsResponse> getNearByEvents(LatLng latLng);

}
