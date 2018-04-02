package com.spade.ja.ui.Home.attractioninner.calendar;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;

/**
 * Created by ehab on 3/14/18.
 */

public interface CalendarContract {

    interface View extends BaseView,ErrorView,ProgressView {

        void setupCalendar(AttractionPaymentData attractionPaymentData);
    }

    interface Presenter {
        void openPaymentView(AttractionPaymentModel attractionPaymentModel);
    }

}
