package com.spade.ja.ui.Home.eventsinner.eventphoneverification;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.usecase.sms.SMS;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 1/25/18.
 */

public class EventPhoneVerificationPresenter extends BasePresenter<EventPhoneVerificationContract.View> implements EventPhoneVerificationContract.Presenter {

    private SMS sms;
    @Inject
    public EventPhoneVerificationPresenter(SMS sms) {
        this.sms = sms;
    }

    private AttractionOrder attractionOrder;
    private EventOrder eventOrder;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null){
            if (extras.getParcelable(Constants.EVENT_ORDER) != null){
                eventOrder = extras.getParcelable(Constants.EVENT_ORDER);
                sms.sendSms(eventOrder.getMobile());
            }else {
                attractionOrder = extras.getParcelable(Constants.ATTRACTION_ORDER);
                sms.sendSms(attractionOrder.getMobile());
            }
        }
    }

    @Override
    public void unSubscribe() {
        sms.unSubscribe();
    }

    public void showEventOrder(EventOrder eventOrder) {
        jaNavigationManager.showOrderView(eventOrder);
    }

    public void showAttractionOrder(AttractionOrder attractionOrder) {
        jaNavigationManager.showAttractionOrderView(attractionOrder);
    }

    @Override
    public void showOrderView() {
        if (eventOrder != null){
            showEventOrder(eventOrder);
        }else {
            showAttractionOrder(attractionOrder);
        }
    }

    @Override
    public void sendMessage() {
        if (eventOrder != null){
            sms.sendSms(eventOrder.getMobile());
        }else {
            sms.sendSms(attractionOrder.getMobile());
        }
    }
}
