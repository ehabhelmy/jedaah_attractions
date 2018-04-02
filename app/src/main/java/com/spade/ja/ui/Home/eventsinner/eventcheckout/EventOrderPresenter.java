package com.spade.ja.ui.Home.eventsinner.eventcheckout;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.order.Data;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.usecase.order.Order;
import com.spade.ja.util.Constants;

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
                getView().hideLoading();
                jaNavigationManager.showEventOrderSuccess();
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().hideLoading();
                getView().showError(message);
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
        getView().showLoading();
        order.order(orderResponseBaseCallback,name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total);
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        EventOrder eventOrder  = extras.getParcelable(Constants.EVENT_ORDER);
        getView().setupOrderView(eventOrder);
    }
}
