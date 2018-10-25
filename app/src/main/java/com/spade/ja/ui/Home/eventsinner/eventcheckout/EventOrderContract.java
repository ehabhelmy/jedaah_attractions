package com.spade.ja.ui.Home.eventsinner.eventcheckout;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 12/27/17.
 */

public interface EventOrderContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupOrderView(EventOrder eventOrder);

        void setupCreditCardPayment(User user,String amount,int id);
    }

    interface Presenter extends Unsubscribable {
        void showPayment();
        void order(String name,String email,String mobileNumber,String numberOfTickets, String paymentMethod, String eventId,String ticketId,String dateId, String nationalId, String total);
    }
}
