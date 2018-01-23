package com.example.ehab.japroject.ui.Home.venueinner.venuedetails;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venuesinner.VenuesInnerResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.EventDetailsAdapter;
import com.example.ehab.japroject.usecase.eventinner.EventInner;
import com.example.ehab.japroject.usecase.like.Like;
import com.example.ehab.japroject.usecase.like.LikeVenues;
import com.example.ehab.japroject.usecase.login.Token;
import com.example.ehab.japroject.usecase.venueinner.VenueInner;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 1/20/18.
 */

public class VenueDetailsPresenter extends BasePresenter<VenueDetailsContract.View> implements VenueDetailsContract.Presenter {

    private VenueInner venueInner;
    private int id;
    private LikeVenues like;

    @Inject
    public VenueDetailsPresenter(VenueInner venueInner, LikeVenues like) {
        this.venueInner = venueInner;
        this.like = like;
    }

    private BaseCallback<VenuesInnerResponse> venuesInnerResponseBaseCallback = new BaseCallback<VenuesInnerResponse>() {
        @Override
        public void onSuccess(VenuesInnerResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().hideLoading();
                    getView().setupVenueDetails(model.getData());
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

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        id = extras.getInt(Constants.VENUE_ID);
        getView().showLoading();
        venueInner.getVenueDetails(venuesInnerResponseBaseCallback,id);
    }

    @Override
    public void openNavigationView(double lat, double lng) {
        jaNavigationManager.openNavigationView(lat,lng);
    }

    @Override
    public void like() {
        like.like(id,likeResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        venueInner.unSubscribe();
        like.unSubscribe();
    }
}
