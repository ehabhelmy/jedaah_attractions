package com.spade.ja.ui.Home.eventsinner;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.listener.BaseView;

/**
 * Created by ehab on 12/22/17.
 */

public interface EventInnerActivityContract {

    interface View extends BaseView {

    }

    interface Presenter {
        void goToHomeActivity();
        BaseFragment getCurrentFragment();
    }
}
