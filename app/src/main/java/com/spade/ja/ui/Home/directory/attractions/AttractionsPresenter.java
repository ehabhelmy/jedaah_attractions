package com.spade.ja.ui.Home.directory.attractions;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.allattractions.AllAttractions;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.usecase.topattractions.TopAttractions;

import javax.inject.Inject;

/**
 * Created by Roma on 1/14/2018.
 */

public class AttractionsPresenter extends BasePresenter<AttractionsContract.View> implements AttractionsContract.Presenter {

    private TopAttractions topAttractions;
    private AllAttractions allAttractions;
    private LikeAttractions likeAttractions;
    private boolean firstFetch = true;


    private BaseCallback<VenuesResponse> topVenuesBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTopAttractions(model.getData());
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().showError(message);
            }
        }
    };


    private BaseCallback<AllVenuesResponse> allVenuesBaseCallback = new BaseCallback<AllVenuesResponse>() {
        @Override
        public void onSuccess(AllVenuesResponse model) {
            if (isViewAlive.get()) {
                if(model !=null){
                    if (model.getSuccess()) {
                        if(firstFetch){
                            getView().setupAllAttractions(model.getData().getAttractions());
                        }
                        else {
                            getView().addAttractions(model.getData().getAttractions());
                        }

                    }
                }

            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().showError(message);
            }
        }
    };

    private BaseCallback<LikeResponse> likeResponseBaseCallback = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            if (isViewAlive.get()){
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public AttractionsPresenter(TopAttractions topAttractions, AllAttractions allAttractions,LikeAttractions likeAttractions) {
        this.topAttractions= topAttractions;
        this.allAttractions = allAttractions;
        this.likeAttractions = likeAttractions;
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        topAttractions.getTopAttractions(topVenuesBaseCallback, true);
        allAttractions.getAllAttractions(allVenuesBaseCallback, true);
    }

    @Override
    public void unSubscribe() {
        topAttractions.unSubscribe();
        allAttractions.unSubscribe();
        likeAttractions.unSubscribe();
    }

    @Override
    public void loadMoreAttractions() {
        firstFetch = false;
        allAttractions.getAllAttractions(allVenuesBaseCallback, true);
    }

    @Override
    public void showAttractionsInner(int id) {
        jaNavigationManager.showAttractionInner(id);
    }

    @Override
    public void attractionLike(int id) {
        likeAttractions.like(id,likeResponseBaseCallback);
    }

    @Override
    public void openFilterAttraction() {
        jaNavigationManager.openFilterAttraction();
    }
}
