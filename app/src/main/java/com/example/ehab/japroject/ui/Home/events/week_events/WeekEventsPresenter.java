package com.example.ehab.japroject.ui.Home.events.week_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.like.Like;
import com.example.ehab.japroject.usecase.weekevents.WeekEvents;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class WeekEventsPresenter extends BasePresenter<WeekEventsContract.View> implements WeekEventsContract.Presenter{

    private WeekEvents weekEvents;
    private Like like;

    private BaseCallback<EventsResponse> weekEventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupWeekEvents(EventsAdapter.convertIntoEventUi(model.getData()));
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
            // do nothing
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public WeekEventsPresenter(WeekEvents weekEvents,Like like) {
        this.weekEvents = weekEvents;
        this.like = like;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        this.weekEvents.getWeekEvents(weekEventsResponseBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        this.weekEvents.unSubscribe();
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
