package com.example.ehab.japroject.ui.Home.events;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by ehab on 12/15/17.
 */

public interface EventsContract {
    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter extends Unsubscribable {

    }
}
