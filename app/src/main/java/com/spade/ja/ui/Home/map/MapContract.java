package com.spade.ja.ui.Home.map;

import android.location.Location;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

import java.util.ArrayList;

/**
 * Created by Roma on 2/3/2018.
 */

public interface  MapContract {
    interface View extends BaseView, ProgressView, ErrorView {
        void setupData(ArrayList<Data> venues);
        Location getCurrentLocation();
    }

    interface Presenter extends Unsubscribable {
        void getAllData();
        void getNearByEvents();
        void getNearByVenues();
        void getNearByAttractions();
        void showEventInner(int id);
        void showVenuesInner(int id);
        void showAttractionInner(int id);
        void like(int id);
        void venuesLike(int id);
        void attractionsLike(int id);
    }
}
