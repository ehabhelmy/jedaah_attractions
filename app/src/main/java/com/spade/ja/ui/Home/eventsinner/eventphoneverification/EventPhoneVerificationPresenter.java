package com.spade.ja.ui.Home.eventsinner.eventphoneverification;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 1/25/18.
 */

public class EventPhoneVerificationPresenter extends BasePresenter<EventPhoneVerificationContract.View> implements EventPhoneVerificationContract.Presenter {

    @Inject
    public EventPhoneVerificationPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    @Override
    public void unSubscribe() {

    }
}
