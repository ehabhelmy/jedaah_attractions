package com.example.ehab.japroject.ui.Home.directory.venues;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.categories.Categories;
import com.example.ehab.japroject.usecase.topvenues.TopVenues;

import javax.inject.Inject;

/**
 * Created by Roma on 1/14/2018.
 */

public class VenuesPresenter extends BasePresenter<VenuesContract.View> implements VenuesContract.Presenter {

    private Categories categories;
    private TopVenues topVenues;
    //  private AllVenues allVenus;

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


    @Inject
    public VenuesPresenter(Categories categories, TopVenues topVenues) {
        this.categories = categories;
        this.topVenues = topVenues;
        // this.allVenus = allVenus;
    }


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        categories.getCategories(categoryBaseCallback, true);
        topVenues.getTopVenues(topVenuesBaseCallback, true);
    }

    @Override
    public void unSubscribe() {
        categories.unSubscribe();
    }
}
