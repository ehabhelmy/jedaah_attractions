package com.example.ehab.japroject.ui.Home.explore;

import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.Unsubscribable;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by ehab on 12/11/17.
 */

public interface ExploreContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupTopEvents(List<Event> events);
        void setupNearbyEvents(List<Event> events);
        boolean isLocationPermissionGranted();
        boolean isLocationEnabled();
        void showErrorLocationNotEnabled();
        void getLatitudeAndLongitude();
        void setupCategories(List<Cats> categories);

    }

    interface Presenter extends Unsubscribable{
        void openLocationSettings();
        void loadNearByEventsAfterLocationEnabled(LatLng latLng);
        void showEventInner(int id);
        void like(int id);
    }
}
