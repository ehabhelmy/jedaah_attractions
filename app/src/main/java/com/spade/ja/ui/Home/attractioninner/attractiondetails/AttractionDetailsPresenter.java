package com.spade.ja.ui.Home.attractioninner.attractiondetails;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionInnerResponse;
import com.spade.ja.datalayer.pojo.response.attractioninner.Data;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.attractioninner.adapter.AttractionPaymentAdapter;
import com.spade.ja.usecase.attractioninner.AttractionInner;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 3/9/18.
 */

public class AttractionDetailsPresenter extends BasePresenter<AttractionDetailsContract.View> implements AttractionDetailsContract.Presenter {

    private AttractionInner attractionInner;
    private int id;
    private LikeAttractions like;
    private Data data;

    @Inject
    public AttractionDetailsPresenter(AttractionInner attractionInner, LikeAttractions like) {
        this.attractionInner = attractionInner;
        this.like = like;
    }

    private BaseCallback<AttractionInnerResponse> attractionInnerResponseBaseCallback = new BaseCallback<AttractionInnerResponse>() {
        @Override
        public void onSuccess(AttractionInnerResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    AttractionDetailsPresenter.this.data = model.getData();
                    getView().hideLoading();
                    getView().setupAttractionDetails(model.getData());
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
        id = extras.getInt(Constants.ATTRACTION_ID);
        getView().showLoading();
        attractionInner.getAttractionDetails(attractionInnerResponseBaseCallback,id);
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
    public void openPaymentView() {
        jaNavigationManager.openCalendarView(AttractionPaymentAdapter.convertToPaymentData(data));
    }

    @Override
    public void unSubscribe() {
        attractionInner.unSubscribe();
        like.unSubscribe();
    }
}
