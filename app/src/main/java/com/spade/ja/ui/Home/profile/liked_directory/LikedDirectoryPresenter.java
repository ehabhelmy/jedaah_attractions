package com.spade.ja.ui.Home.profile.liked_directory;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.explore.adapter.EventsAdapter;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.usecase.like.LikeVenues;
import com.spade.ja.usecase.likeddirectory.LikedAttractions;
import com.spade.ja.usecase.likeddirectory.LikedVenues;
import com.spade.ja.usecase.likedevents.LikedEvents;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class LikedDirectoryPresenter extends BasePresenter<LikedDirectoryContract.View> implements LikedDirectoryContract.Presenter {

    private LikedVenues likedVenues;
    private LikedAttractions likedAttractions;
    private LikeAttractions like;
    private LikeVenues likeVenues;

    @Inject
    public LikedDirectoryPresenter(LikedVenues likedVenues, LikedAttractions likedAttractions, LikeAttractions like, LikeVenues likeVenues) {
        this.likedVenues = likedVenues;
        this.likedAttractions = likedAttractions;
        this.like = like;
        this.likeVenues = likeVenues;
    }

    private BaseCallback<VenuesResponse> attractionsResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (model.getData().size() > 0) {
                        getView().setupAllAttractions(model.getData());
                    }else {
                        getView().showNoData();
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

    private BaseCallback<VenuesResponse> venuesResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (model.getData().size() > 0) {
                        getView().setupAllVenues(model.getData());
                    }else {
                        getView().showNoData();
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
    private  BaseCallback<LikeResponse> likeResponseBaseCallback  = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            //TODO : update ui
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
        likedVenues.getLikedVenues(venuesResponseBaseCallback,true);
        likedAttractions.getLikedAttractions(attractionsResponseBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        like.unSubscribe();
        likeVenues.unSubscribe();
        likedAttractions.unSubscribe();
        likedVenues.unSubscribe();
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }
}
