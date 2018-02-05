package com.spade.ja.ui.Home.profile.liked_events;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.explore.adapter.EventsAdapter;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.likedevents.LikedEvents;

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
