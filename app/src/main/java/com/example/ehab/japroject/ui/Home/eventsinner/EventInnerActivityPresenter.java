package com.example.ehab.japroject.ui.Home.eventsinner;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/22/17.
 */

public class EventInnerActivityPresenter extends BasePresenter<EventInnerActivityContract.View> implements EventInnerActivityContract.Presenter {

    @Inject
    public EventInnerActivityPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            jaNavigationManager.showEventDetails(extras.getInt(Constants.EVENT_ID));
        }
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }
}
