package com.spade.ja.ui.Home.profile.liked_events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class LikedEventsContract {

    interface View extends BaseView, ErrorView, ProgressView {
        void setupAllEvents(List<Event> events);
        void showNoEvents();
    }

    interface Presenter extends Unsubscribable {
        void like(int id);
        void showEventInner(int id);
    }
}
