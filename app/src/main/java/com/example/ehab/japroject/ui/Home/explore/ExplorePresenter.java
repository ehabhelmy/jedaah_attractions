package com.example.ehab.japroject.ui.Home.explore;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.TopEventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.topevents.TopEvents;

import javax.inject.Inject;

/**
 * Created by ehab on 12/11/17.
 */

public class ExplorePresenter extends BasePresenter<ExploreContract.View> implements ExploreContract.Presenter {

    private TopEvents topEvents;

    private BaseCallback<TopEventsResponse> eventsResponseBaseCallback = new BaseCallback<TopEventsResponse>() {
        @Override
        public void onSuccess(TopEventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTopEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                } else {
                    getView().showError();
                }
            }
        }

        @Override
        public void onError() {
            if (isViewAlive.get()){
                getView().showError();
            }
        }
    };

    @Inject
    public ExplorePresenter(TopEvents topEvents) {
        this.topEvents = topEvents;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        //TODO : should call all related use cases to fetch the data
        topEvents.getTopEvents(eventsResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        topEvents.unSubscribe();
    }
}
