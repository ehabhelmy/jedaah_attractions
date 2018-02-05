package com.spade.ja.ui.Home.events.all_events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.usecase.Unsubscribable;

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
