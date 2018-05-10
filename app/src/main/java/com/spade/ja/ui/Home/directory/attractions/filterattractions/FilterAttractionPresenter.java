package com.spade.ja.ui.Home.directory.attractions.filterattractions;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.filter.venues.FilterVenuesResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.map.DataConverter;
import com.spade.ja.usecase.categories.Categories;
import com.spade.ja.usecase.filter.filterattractions.FilterAttraction;
import com.spade.ja.usecase.like.LikeAttractions;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ehab on 4/1/18.
 */

public class FilterAttractionPresenter extends BasePresenter<FilterAttractionContract.View> implements FilterAttractionContract.Presenter{

    private Categories categories;
    private FilterAttraction filterVenues;
    private LikeAttractions like;

    @Inject
    public FilterAttractionPresenter(Categories categories, FilterAttraction filterVenues,LikeAttractions like) {
        this.categories = categories;
        this.filterVenues = filterVenues;
        this.like = like;
    }

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


    private BaseCallback<FilterVenuesResponse> filterEventsResponseBaseCallback  = new BaseCallback<FilterVenuesResponse>() {
        @Override
        public void onSuccess(FilterVenuesResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().showResults(DataConverter.convertIntoVenueUiFilter(model.getData().getResult()));
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
        filterVenues.unSubscribe();
        like.unSubscribe();
    }

    @Override
    public void filterAttractions(boolean weeklySuggest,List<Integer> categoryId,Double lat,Double lng) {
        filterVenues.filterAttractions(weeklySuggest,categoryId,filterEventsResponseBaseCallback,lat,lng);
    }

    @Override
    public void showAttractionInner(int id) {
        jaNavigationManager.showAttractionInner(id);
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }
}
