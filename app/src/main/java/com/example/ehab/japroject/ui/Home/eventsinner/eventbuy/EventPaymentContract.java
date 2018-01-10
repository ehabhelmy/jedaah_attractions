package com.example.ehab.japroject.ui.Home.eventsinner.eventbuy;

import com.example.ehab.japroject.datalayer.pojo.response.login.User;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;

/**
 * Created by ehab on 12/25/17.
 */

public interface EventPaymentContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupPaymentView(PaymentData paymentData);
        void setupUserData(User user);
    }

    interface Presenter {
        void showOrderView(EventOrder eventOrder);
    }
}
