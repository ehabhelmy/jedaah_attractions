package com.spade.ja.ui.Home.attractioninner.attractionpayment;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;

/**
 * Created by ehab on 3/14/18.
 */

public interface AttractionPaymentContract {


    interface View extends BaseView,ErrorView,ProgressView {
        void setupPaymentView(AttractionPaymentModel model);
        void setupUserData(User user);
    }

    interface Presenter {
        void showOrderView(AttractionOrder attractionOrder);
    }
}
