package com.spade.ja.ui.Home.map;

import android.location.Location;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

import java.util.ArrayList;

/**
 * Created by Roma on 2/3/2018.
 */

public interface MapContract {
    interface View extends BaseView, ProgressView, ErrorView {
        void setupData(ArrayList<Data> venues);
        Location getCurrentLocation();
    }

    interface Presenter {
        void getAllData();

        void getNearByEvents();

        void getNearByVenues();

        void getNearByAttractions();
    }
}
