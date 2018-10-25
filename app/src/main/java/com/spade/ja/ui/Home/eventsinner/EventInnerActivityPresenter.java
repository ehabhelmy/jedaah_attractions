package com.spade.ja.ui.Home.eventsinner;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.order.Data;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.paymentcredit.PaymentCreditUseCase;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/22/17.
 */

public class EventInnerActivityPresenter extends BasePresenter<EventInnerActivityContract.View> implements EventInnerActivityContract.Presenter {

    private PaymentCreditUseCase paymentCreditUseCase;

    @Inject
    public EventInnerActivityPresenter(PaymentCreditUseCase paymentCreditUseCase) {
        this.paymentCreditUseCase = paymentCreditUseCase;
    }

    private BaseCallback<EventChangeStatusResponse> baseModelBaseCallback = new BaseCallback<EventChangeStatusResponse>() {
        @Override
        public void onSuccess(EventChangeStatusResponse model) {
            if (isViewAlive.get()){
                getView().hideLoading();
                jaNavigationManager.showEventOrderSuccess();
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };


    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            jaNavigationManager.showEventDetails(extras.getInt(Constants.EVENT_ID));
        }
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return jaNavigationManager.getCurrentFragmentOnInner();
    }

    @Override
    public void changeCreditCardStatus(String orderid, String status) {
        getView().showLoading();
        paymentCreditUseCase.changeCreditCardOrder(orderid, status,baseModelBaseCallback);
    }

    @Override
    public void unSubscribe() {
        paymentCreditUseCase.unSubscribe();
    }
}
