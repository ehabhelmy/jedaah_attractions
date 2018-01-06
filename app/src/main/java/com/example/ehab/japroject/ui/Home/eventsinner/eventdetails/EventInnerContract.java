package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.pojo.EventDetails;
import com.example.ehab.japroject.usecase.Unsubscribable;

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
