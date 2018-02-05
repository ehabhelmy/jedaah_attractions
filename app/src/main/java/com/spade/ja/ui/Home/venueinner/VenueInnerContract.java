package com.spade.ja.ui.Home.venueinner;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

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
