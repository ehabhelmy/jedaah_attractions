package com.example.ehab.japroject.ui.Home.venueinner.venuedetails;

import com.example.ehab.japroject.datalayer.pojo.response.venuesinner.Data;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by ehab on 1/20/18.
 */

public class VenueDetailsContract {
    interface View extends BaseView,ErrorView,ProgressView {
        void setupVenueDetails(Data data);
    }

    interface Presenter extends Unsubscribable {
        void openNavigationView(double lat,double lng);
        void like();
    }
}
