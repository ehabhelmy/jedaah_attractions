package com.spade.ja.ui.Home.eventsinner.eventcheckout;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.order.Data;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.usecase.LoggedUser.LoggedUser;
import com.spade.ja.usecase.order.Order;
import com.spade.ja.util.Constants;
import com.spade.ja.util.PaymentGateWay;

import javax.inject.Inject;

/**
 * Created by ehab on 12/27/17.
 */

public class EventOrderPresenter extends BasePresenter<EventOrderContract.View> implements EventOrderContract.Presenter {

    private Order order;
    private LoggedUser loggedUser;
    private String paymentMethod;
    private String amount;

    private BaseCallback<Data> orderResponseBaseCallback = new BaseCallback<Data>() {
        @Override
        public void onSuccess(Data data) {
            if (isViewAlive.get()){
                getView().hideLoading();
                if (paymentMethod.equals("credit_card")){
                    loggedUser.getLoggedUser(new BaseCallback<User>() {
                        @Override
                        public void onSuccess(User model) {
                            if (isViewAlive.get()){
                                getView().setupCreditCardPayment(model,amount,data.getOrder().getId());
                            }
                        }

                        @Override
                        public void onError(String message) {
                            if (isViewAlive.get()){
                                getView().showError(message);
                            }
                        }
                    });
                }else {
                    jaNavigationManager.showEventOrderSuccess();
                }
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
    public EventOrderPresenter(Order order, LoggedUser loggedUser) {
        this.order = order;
        this.loggedUser = loggedUser;
    }

    @Override
    public void unSubscribe() {
        order.unSubscribe();
        loggedUser.unSubscribe();
    }

    @Override
    public void showPayment() {
        jaNavigationManager.openPaymentView(null);
    }

    @Override
    public void order(String name, String email, String mobileNumber, String numberOfTickets, String paymentMethod, String eventId, String ticketId, String dateId, String nationalId, String total) {
        getView().showLoading();
        this.paymentMethod = paymentMethod;
        this.amount = total;
        order.order(orderResponseBaseCallback,name,email,mobileNumber,numberOfTickets,paymentMethod,eventId,ticketId,dateId,nationalId,total);
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        EventOrder eventOrder  = extras.getParcelable(Constants.EVENT_ORDER);
        getView().setupOrderView(eventOrder);
    }
}
