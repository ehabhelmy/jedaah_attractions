package com.example.ehab.japroject.ui.Home.profile.my_tickets;

import com.example.ehab.japroject.datalayer.pojo.response.history.upcoming.Datum;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.example.ehab.japroject.usecase.Unsubscribable;

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
