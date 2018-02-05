package com.spade.ja.ui.Home.eventsinner.eventbuy;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;

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
