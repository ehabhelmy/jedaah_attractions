package com.spade.ja.ui.Home.eventsinner;

import android.os.Bundle;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.util.Constants;

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

    @Override
    public BaseFragment getCurrentFragment() {
        return jaNavigationManager.getCurrentFragmentOnInner();
    }
}
