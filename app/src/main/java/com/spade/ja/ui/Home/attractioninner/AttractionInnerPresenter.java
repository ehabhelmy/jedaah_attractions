package com.spade.ja.ui.Home.attractioninner;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.attractionconfirm.AttractionConfirmOrderResponse;
import com.spade.ja.datalayer.pojo.response.eventcreditconfirm.EventChangeStatusResponse;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.paymentcredit.PaymentCreditAttractionUseCase;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 3/9/18.
 */

public class AttractionInnerPresenter extends BasePresenter<AttractionInnerContract.View> implements AttractionInnerContract.Presenter {

    private PaymentCreditAttractionUseCase paymentCreditAttractionUseCase;

    @Inject
    public AttractionInnerPresenter(PaymentCreditAttractionUseCase paymentCreditAttractionUseCase) {
        this.paymentCreditAttractionUseCase = paymentCreditAttractionUseCase;
    }

    private BaseCallback<AttractionConfirmOrderResponse> baseModelBaseCallback = new BaseCallback<AttractionConfirmOrderResponse>() {
        @Override
        public void onSuccess(AttractionConfirmOrderResponse model) {
            if (isViewAlive.get()){
                getView().hideLoading();
                jaNavigationManager.showAttractionOrderSuccess();
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
            jaNavigationManager.showAttractionDetails(extras.getInt(Constants.ATTRACTION_ID));
        }
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return jaNavigationManager.getCurrentFragmentOnInnerAttraction();
    }

    @Override
    public void changeCreditCardStatus(String orderid, String status) {
        getView().showLoading();
        paymentCreditAttractionUseCase.changeCreditCardOrder(orderid, status,baseModelBaseCallback);
    }
}
