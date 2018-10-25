package com.spade.ja.ui.Home.eventsinner;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 12/22/17.
 */

public interface EventInnerActivityContract {

    interface View extends BaseView,ProgressView,ErrorView {

    }

    interface Presenter extends Unsubscribable {
        void goToHomeActivity();
        BaseFragment getCurrentFragment();
        void changeCreditCardStatus(String orderid,String status);
    }
}
