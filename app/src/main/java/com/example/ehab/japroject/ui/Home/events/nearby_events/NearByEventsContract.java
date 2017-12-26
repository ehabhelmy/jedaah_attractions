package com.example.ehab.japroject.ui.Home.events.nearby_events;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.Unsubscribable;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by ehab on 12/16/17.
 */

public class NearByEventsContract {

    interface View extends BaseView,ErrorView,ProgressView{
        void setupNearByEvents(List<Event> events);

        boolean isLocationPermissionGranted();

        boolean isLocationEnabled();

        void getLatitudeAndLongitude();

        void showErrorLocationNotEnabled();
    }

    interface Presenter extends Unsubscribable {
        void loadNearByEventsAfterLocationEnabled(LatLng latLng);

        void openLocationSettings();

        void showEventInner(int id);

        void like(int id);

    }
}
