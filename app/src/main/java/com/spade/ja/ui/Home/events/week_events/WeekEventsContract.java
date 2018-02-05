package com.spade.ja.ui.Home.events.week_events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Romisaa on 12/16/2017.
 */

public interface WeekEventsContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupWeekEvents(List<Event> eventList);
    }

    interface Presenter extends Unsubscribable {
        void showEventInner(int id);
        void like(int id);
    }
}
