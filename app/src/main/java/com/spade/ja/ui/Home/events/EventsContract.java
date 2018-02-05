package com.spade.ja.ui.Home.events;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 12/15/17.
 */

public interface EventsContract {
    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter extends Unsubscribable {

    }
}
