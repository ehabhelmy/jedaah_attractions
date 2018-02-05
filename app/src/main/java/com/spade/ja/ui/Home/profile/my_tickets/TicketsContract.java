package com.spade.ja.ui.Home.profile.my_tickets;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class TicketsContract {

    interface View extends BaseView, ErrorView, ProgressView {
        void setupUpcomingList(List<HistoryEventsUi> data);
        void setupPastList(List<HistoryEventsUi> data);
    }

    interface Presenter extends Unsubscribable {

    }
}
