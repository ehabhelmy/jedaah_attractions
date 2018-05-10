package com.spade.ja.ui.Home.eventsinner.eventbuy;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.usecase.LoggedUser.LoggedUser;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/25/17.
 */

public class EventPaymentPresenter extends BasePresenter<EventPaymentContract.View> implements EventPaymentContract.Presenter {

    private LoggedUser loggedUser;
    private BaseCallback<User> userBaseCallback = new BaseCallback<User>() {
        @Override
        public void onSuccess(User model) {
            if (isViewAlive.get()){
                getView().setupUserData(model);
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };
    @Inject
    public EventPaymentPresenter(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        PaymentData paymentData = extras.getParcelable(Constants.EVENT_PAYMENT);
        getView().setupPaymentView(paymentData);
        loggedUser.getLoggedUser(userBaseCallback);
    }

    @Override
    public void showOrderView(EventOrder eventOrder) {
        jaNavigationManager.showPhoneVerfication(eventOrder);
    }
}
