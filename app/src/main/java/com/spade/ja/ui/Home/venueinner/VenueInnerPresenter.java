package com.spade.ja.ui.Home.venueinner;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 1/20/18.
 */

public class VenueInnerPresenter extends BasePresenter<VenueInnerContract.View> implements VenueInnerContract.Presenter {

    @Inject
    public VenueInnerPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            jaNavigationManager.showVenueDetails(extras.getInt(Constants.VENUE_ID));
        }
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }

}
