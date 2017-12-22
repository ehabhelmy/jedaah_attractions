package com.example.ehab.japroject.ui.Home.eventsinner;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.Data;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.ui.Home.eventsinner.pojo.EventDetails;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by ehab on 12/20/17.
 */

public interface EventInnerContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupEventsInner(EventDetails data);
    }

    interface Presenter extends Unsubscribable{

    }
}
