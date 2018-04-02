package com.spade.ja.ui.Home.attractioninner.attractionpayment;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.usecase.LoggedUser.LoggedUser;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentPresenter extends BasePresenter<AttractionPaymentContract.View> implements AttractionPaymentContract.Presenter {

    private LoggedUser loggedUser;
    private BaseCallback<User> userBaseCallback = new BaseCallback<User>() {
        @Override
        public void onSuccess(User model) {
            if (isViewAlive.get()){
                getView().setupUserData(model);
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };
    @Inject
    public AttractionPaymentPresenter(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            AttractionPaymentModel paymentData = extras.getParcelable(Constants.ATTRACTION_PAYMENT);
            getView().setupPaymentView(paymentData);
        }
        loggedUser.getLoggedUser(userBaseCallback);
    }

    @Override
    public void showOrderView(AttractionOrder attractionOrder) {
        jaNavigationManager.showAttractionOrderView(attractionOrder);
    }
}
