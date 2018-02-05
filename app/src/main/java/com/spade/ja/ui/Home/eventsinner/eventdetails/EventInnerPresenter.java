package com.spade.ja.ui.Home.eventsinner.eventdetails;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventdetails.adapter.EventDetailsAdapter;
import com.spade.ja.usecase.eventinner.EventInner;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.login.Token;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/20/17.
 */

public class EventInnerPresenter extends BasePresenter<EventInnerContract.View> implements EventInnerContract.Presenter {


    private EventInner eventInner;
    private Token token;
    private int id;
    private Like like;
    private BaseCallback<EventInnerResponse> eventInnerResponseBaseCallback = new BaseCallback<EventInnerResponse>() {
        @Override
        public void onSuccess(EventInnerResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().hideLoading();
                    getView().setupEventsInner(EventDetailsAdapter.convertIntoEventDetailsUi(model.getData()));
                    getView().savePaymentDetails(EventDetailsAdapter.setupPaymentPojo(model.getData()));
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };
    private BaseCallback<LikeResponse> likeResponseBaseCallback = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            // do nothing
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public EventInnerPresenter(EventInner eventInner,Token token,Like like) {
        this.eventInner = eventInner;
        this.token = token;
        this.like = like;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        //TODO : get id from extras and call api event inner
        id = extras.getInt(Constants.EVENT_ID);
        getView().showLoading();
        eventInner.getEventDetails(eventInnerResponseBaseCallback,id);
    }

    @Override
    public void unSubscribe() {
        eventInner.unSubscribe();
    }

    @Override
    public void openNavigationView(double lat, double lng) {
        jaNavigationManager.openNavigationView(lat,lng);
    }

    @Override
    public void openPaymentView(PaymentData paymentData) {
        if (token.getToken() == null) {
            jaNavigationManager.goToAuthActivity(token.getToken());
        }else {
            jaNavigationManager.openPaymentView(paymentData);
        }
    }

    @Override
    public void like() {
        like.like(id,likeResponseBaseCallback);
    }
}
