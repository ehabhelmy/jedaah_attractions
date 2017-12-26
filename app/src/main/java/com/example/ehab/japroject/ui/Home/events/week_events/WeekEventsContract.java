package com.example.ehab.japroject.ui.Home.events.week_events;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.Unsubscribable;

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
