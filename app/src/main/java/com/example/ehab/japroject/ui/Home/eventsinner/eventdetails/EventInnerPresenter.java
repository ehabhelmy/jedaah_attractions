package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.EventDetailsAdapter;
import com.example.ehab.japroject.usecase.eventinner.EventInner;
import com.example.ehab.japroject.usecase.like.Like;
import com.example.ehab.japroject.usecase.login.Token;
import com.example.ehab.japroject.util.Constants;

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
