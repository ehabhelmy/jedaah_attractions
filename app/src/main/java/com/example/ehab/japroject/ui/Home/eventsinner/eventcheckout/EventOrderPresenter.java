package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.order.Order;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/27/17.
 */

public class EventOrderPresenter extends BasePresenter<EventOrderContract.View> implements EventOrderContract.Presenter {

    private Order order;

    @Inject
    public EventOrderPresenter(Order order) {
        this.order = order;
    }

    @Override
    public void unSubscribe() {
        order.unSubscribe();
    }

    @Override
    public void showPayment() {
        jaNavigationManager.openPaymentView(null);
    }

    @Override
    public void order() {

    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        EventOrder eventOrder  = extras.getParcelable(Constants.EVENT_ORDER);
        getView().setupOrderView(eventOrder);
    }
}
