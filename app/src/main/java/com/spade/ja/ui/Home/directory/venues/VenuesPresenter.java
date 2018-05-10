package com.spade.ja.ui.Home.directory.venues;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.allvenues.AllVenuesResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.allvenues.AllVenues;
import com.spade.ja.usecase.categories.Categories;
import com.spade.ja.usecase.like.LikeVenues;
import com.spade.ja.usecase.topvenues.TopVenues;

import javax.inject.Inject;

/**
 * Created by Roma on 1/14/2018.
 */

public class VenuesPresenter extends BasePresenter<VenuesContract.View> implements VenuesContract.Presenter {

    private Categories categories;
    private TopVenues topVenues;
    private AllVenues allVenues;
    private LikeVenues likeVenues;
    private boolean firstFetch = true;

    private BaseCallback<Category> categoryBaseCallback = new BaseCallback<Category>() {
        @Override
        public void onSuccess(Category model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupCategories(model.getData());
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

    private BaseCallback<VenuesResponse> topVenuesBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTopVenues(model.getData());
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
                            getView().setupAllVenues(model.getData().getVenues());
                        }
                        else {
                            getView().addVenues(model.getData().getVenues());
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
    public VenuesPresenter(Categories categories, TopVenues topVenues, AllVenues allVenues, LikeVenues likeVenues) {
        this.categories = categories;
        this.topVenues = topVenues;
        this.allVenues = allVenues;
        this.likeVenues = likeVenues;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        categories.getCategories(categoryBaseCallback, true);
        topVenues.getTopVenues(topVenuesBaseCallback, true);
        allVenues.getAllVenues(allVenuesBaseCallback, true);
    }

    @Override
    public void unSubscribe() {
        categories.unSubscribe();
        topVenues.unSubscribe();
        allVenues.unSubscribe();
        likeVenues.unSubscribe();
    }

    @Override
    public void loadMoreVenues() {
        firstFetch = false;
        allVenues.getAllVenues(allVenuesBaseCallback, true);
    }

    @Override
    public void showVenueInner(int id) {
        jaNavigationManager.showVenueInner(id);
    }

    @Override
    public void venueLike(int id) {
        likeVenues.like(id,likeResponseBaseCallback);
    }

    @Override
    public void openFilterVenues() {
        jaNavigationManager.openFilterVenues();
    }
}
