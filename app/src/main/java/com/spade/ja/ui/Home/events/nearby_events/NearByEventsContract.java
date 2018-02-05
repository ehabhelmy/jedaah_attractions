package com.spade.ja.ui.Home.events.nearby_events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.usecase.Unsubscribable;
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
