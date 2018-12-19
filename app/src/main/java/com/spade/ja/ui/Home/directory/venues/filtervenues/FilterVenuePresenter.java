package com.spade.ja.ui.Home.directory.venues.filtervenues;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.map.DataConverter;
import com.spade.ja.usecase.categories.Categories;
import com.spade.ja.usecase.filter.filtervenues.FilterVenues;
import com.spade.ja.usecase.like.LikeVenues;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ehab on 2/27/18.
 */

public class FilterVenuePresenter extends BasePresenter<FilterVenueContract.View> implements FilterVenueContract.Presenter {

    private Categories categories;

    @Inject
    public FilterVenuePresenter(Categories categories, FilterVenues filterVenues) {
        this.categories = categories;
    }

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

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        categories.getCategories(categoryBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        categories.unSubscribe();
    }

}
