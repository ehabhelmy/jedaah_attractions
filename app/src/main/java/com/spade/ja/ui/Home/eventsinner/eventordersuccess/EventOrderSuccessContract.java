package com.spade.ja.ui.Home.eventsinner.eventordersuccess;

import com.spade.ja.ui.Base.listener.BaseView;

/**
 * Created by ehab on 1/10/18.
 */

public interface EventOrderSuccessContract {

    interface View extends BaseView {

    }

    interface Presenter {
        void bookMore();
    }
}
