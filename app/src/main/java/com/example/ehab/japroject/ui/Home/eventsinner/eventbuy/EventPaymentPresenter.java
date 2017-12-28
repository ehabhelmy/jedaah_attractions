package com.example.ehab.japroject.ui.Home.eventsinner.eventbuy;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/25/17.
 */

public class EventPaymentPresenter extends BasePresenter<EventPaymentContract.View> implements EventPaymentContract.Presenter {

    @Inject
    public EventPaymentPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        PaymentData paymentData = extras.getParcelable(Constants.EVENT_PAYMENT);
        getView().setupPaymentView(paymentData);
    }

    @Override
    public void showOrderView(EventOrder eventOrder) {
        jaNavigationManager.showOrderView(eventOrder);
    }
}
