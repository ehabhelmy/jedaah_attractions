package com.spade.ja.ui.Home.attractioninner;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 3/9/18.
 */

public interface AttractionInnerContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter {
        void goToHomeActivity();
        BaseFragment getCurrentFragment();
        void changeCreditCardStatus(String orderid,String status);
    }
}
