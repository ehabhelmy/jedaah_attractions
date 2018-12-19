package com.spade.ja.ui.Home.events;

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
 * Created by ehab on 12/15/17.
 */

public class EventsPresenter extends BasePresenter<EventsContract.View> implements EventsContract.Presenter {

    private FilterEvents filterEvents;
    private Like like;

    @Inject
    public EventsPresenter(FilterEvents filterEvents,Like like) {
        this.like = like;
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

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    @Override
    public void unSubscribe() {
        like.unSubscribe();
        filterEvents.unSubscribe();
    }

    @Override
    public void filterEvents(boolean weeklySuggest, List<Integer> categoryId, int monthNumber, Double lat, Double lng) {
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

    @Override
    public void openFilterEvents() {
        jaNavigationManager.openFilterEvents();
    }
}
