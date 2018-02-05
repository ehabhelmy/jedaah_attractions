package com.spade.ja.ui.Home.events.all_events;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.allevents.AllEventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.explore.adapter.EventsAdapter;
import com.spade.ja.usecase.allevents.AllEvents;
import com.spade.ja.usecase.like.Like;

import javax.inject.Inject;

/**
 * Created by ehab on 12/16/17.
 */

public class AllEventsPresenter extends BasePresenter<AllEventsContract.View> implements AllEventsContract.Presenter {

    private AllEvents allEvents;
    private Like like;
    private boolean firstFetch = true;
    private BaseCallback<AllEventsResponse> eventsResponseBaseCallback = new BaseCallback<AllEventsResponse>() {
        @Override
        public void onSuccess(AllEventsResponse model) {
            if (isViewAlive.get()) {
                if (model == null) {
                    getView().addEvents(null);
                } else {
                    if (model.getSuccess()) {
                        if (firstFetch) {
                            getView().setupAllEvents(EventsAdapter.convertIntoEventUiAll(model.getData().getEvents()));
                        } else {
                            getView().addEvents(EventsAdapter.convertIntoEventUiAll(model.getData().getEvents()));
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
    public AllEventsPresenter(AllEvents allEvents,Like like) {
        this.allEvents = allEvents;
        this.like = like;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        allEvents.getAllEvents(eventsResponseBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        allEvents.unSubscribe();
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
    public void addEvents() {
        firstFetch = false;
        allEvents.getAllEvents(eventsResponseBaseCallback,true);
    }
}
