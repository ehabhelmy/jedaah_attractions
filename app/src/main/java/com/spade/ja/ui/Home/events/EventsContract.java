package com.spade.ja.ui.Home.events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 12/15/17.
 */

public interface EventsContract {
    interface View extends BaseView,ErrorView,ProgressView {
        void showResults(List<Data> events);
    }

    interface Presenter extends Unsubscribable {
        void showEventInner(int id);
        void like(int id);
        void openFilterEvents();
        void filterEvents(boolean weeklySuggest, List<Integer> categoryId, int monthNumber, Double lat, Double lng);
    }
}
