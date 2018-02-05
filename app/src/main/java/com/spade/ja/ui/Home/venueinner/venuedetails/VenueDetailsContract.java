package com.spade.ja.ui.Home.venueinner.venuedetails;

import com.spade.ja.datalayer.pojo.response.venuesinner.Data;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

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
