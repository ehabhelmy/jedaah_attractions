package com.spade.ja.ui.Home.events.week_events;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.explore.adapter.EventsAdapter;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.weekevents.WeekEvents;

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
