package com.example.ehab.japroject.ui.Home.venueinner;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 1/20/18.
 */

public interface VenueInnerContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter {
        void goToHomeActivity();
    }
}
