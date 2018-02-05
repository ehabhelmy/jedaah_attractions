package com.spade.ja.ui.Home.eventsinner.eventdetails;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventdetails.pojo.EventDetails;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 12/20/17.
 */

public interface EventInnerContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupEventsInner(EventDetails data);
        void savePaymentDetails(PaymentData paymentData);
    }

    interface Presenter extends Unsubscribable{
        void openNavigationView(double lat,double lng);
        void openPaymentView(PaymentData paymentData);
        void like();
    }
}
