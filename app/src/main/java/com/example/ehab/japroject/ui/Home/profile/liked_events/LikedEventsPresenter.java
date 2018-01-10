package com.example.ehab.japroject.ui.Home.profile.liked_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.like.Like;
import com.example.ehab.japroject.usecase.likedevents.LikedEvents;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class LikedEventsPresenter extends BasePresenter<LikedEventsContract.View> implements LikedEventsContract.Presenter{

    private LikedEvents likedEvents;
    private Like like;

    @Inject
    public LikedEventsPresenter(LikedEvents likedEvents, Like like) {
        this.likedEvents = likedEvents;
        this.like = like;
    }

    private BaseCallback<EventsResponse> eventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (model.getData().size() > 0) {
                        getView().setupAllEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                    }else {
                        getView().showNoEvents();
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
    private  BaseCallback<LikeResponse> likeResponseBaseCallback  = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            //TODO : update ui
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        likedEvents.getLikedEvents(eventsResponseBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        like.unSubscribe();
        likedEvents.unSubscribe();
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }
}
