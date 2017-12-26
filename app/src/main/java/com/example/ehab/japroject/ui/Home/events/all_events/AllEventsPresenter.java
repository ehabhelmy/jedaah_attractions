package com.example.ehab.japroject.ui.Home.events.all_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.allevents.AllEvents;
import com.example.ehab.japroject.usecase.like.Like;

import javax.inject.Inject;

/**
 * Created by ehab on 12/16/17.
 */

public class AllEventsPresenter extends BasePresenter<AllEventsContract.View> implements AllEventsContract.Presenter {

    private AllEvents allEvents;
    private Like like;
    private BaseCallback<EventsResponse> eventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupAllEvents(EventsAdapter.convertIntoEventUi(model.getData()));
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
}
