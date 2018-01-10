package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.order.Data;
import com.example.ehab.japroject.datalayer.pojo.response.order.OrderResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
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

    private BaseCallback<Data> orderResponseBaseCallback = new BaseCallback<Data>() {
        @Override
        public void onSuccess(Data model) {
            if (isViewAlive.get()){
                jaNavigationManager.showEventOrderSuccess();
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError("Server Error");
            }
        }
    };

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
    public void order(String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total) {
        order.order(orderResponseBaseCallback,name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total);
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        EventOrder eventOrder  = extras.getParcelable(Constants.EVENT_ORDER);
        getView().setupOrderView(eventOrder);
    }
}
