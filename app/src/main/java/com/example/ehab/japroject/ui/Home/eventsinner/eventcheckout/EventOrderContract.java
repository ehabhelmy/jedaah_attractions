package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by ehab on 12/27/17.
 */

public interface EventOrderContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupOrderView(EventOrder eventOrder);
    }

    interface Presenter extends Unsubscribable {
        void showPayment();
        void order(String name,String email,String mobileNumber,String numberOfTickets, String paymentMethod, String eventId,String ticketId,String dateId, String nationalId, String total);
    }
}
