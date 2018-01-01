package com.example.ehab.japroject.ui.Home.events.all_events;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 12/16/17.
 */

public interface AllEventsContract {

    interface View extends BaseView, ErrorView, ProgressView {
        void setupAllEvents(List<Event> events);
        void addEvents(List<Event> events);
    }

    interface Presenter extends Unsubscribable {
        void showEventInner(int id);
        void like(int id);
        void addEvents();
    }
}
