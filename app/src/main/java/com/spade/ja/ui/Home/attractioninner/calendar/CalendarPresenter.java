package com.spade.ja.ui.Home.attractioninner.calendar;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 3/14/18.
 */

public class CalendarPresenter extends BasePresenter<CalendarContract.View> implements CalendarContract.Presenter {

    @Inject
    public CalendarPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            AttractionPaymentData attractionPaymentData = extras.getParcelable(Constants.CALENDAR_PAYMENT);
            getView().setupCalendar(attractionPaymentData);
        }
    }

    @Override
    public void openPaymentView(AttractionPaymentModel attractionPaymentModel) {
        jaNavigationManager.openAttractionPaymentView(attractionPaymentModel);
    }
}
