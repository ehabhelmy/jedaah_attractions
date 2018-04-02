package com.spade.ja.ui.Home.events.filterevents;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.filter.events.FilterEventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.map.DataConverter;
import com.spade.ja.usecase.categories.Categories;
import com.spade.ja.usecase.filter.filterevents.FilterEvents;
import com.spade.ja.usecase.like.Like;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ehab on 2/18/18.
 */

public class FilterEventPresenter extends BasePresenter<FilterEventContract.View> implements FilterEventContract.Presenter {

    private Categories categories;
    private FilterEvents filterEvents;
    private Like like;

    @Inject
    public FilterEventPresenter(Categories categories, FilterEvents filterEvents) {
        this.categories = categories;
        this.filterEvents = filterEvents;
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


    private BaseCallback<FilterEventsResponse> filterEventsResponseBaseCallback  = new BaseCallback<FilterEventsResponse>() {
        @Override
        public void onSuccess(FilterEventsResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().showResults(DataConverter.convertIntoEventUiFilter(model.getData().getResult()));
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
        filterEvents.unSubscribe();
    }

    @Override
    public void filterEvents(boolean weeklySuggest,List<Integer> categoryId, int monthNumber,Double lat,Double lng) {
        filterEvents.filterEvents(weeklySuggest,categoryId,lat,lng,monthNumber,filterEventsResponseBaseCallback);
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }
}
