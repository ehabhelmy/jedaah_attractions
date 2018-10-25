package com.spade.ja.ui.Home.attractioninner.AttractionOrder;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.request.attractionorder.AttractionOrderRequest;
import com.spade.ja.datalayer.pojo.request.attractionorder.Ticket;
import com.spade.ja.datalayer.pojo.response.attractionorder.AttractionOrderResponse;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.viewtickets.Datum;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.usecase.LoggedUser.LoggedUser;
import com.spade.ja.usecase.viewtickets.ViewTickets;
import com.spade.ja.util.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionOrderPresenter extends BasePresenter<AttractionOrderContract.View> implements AttractionOrderContract.Presenter{

    private ViewTickets viewTickets;
    private com.spade.ja.usecase.attractionorder.AttractionOrder attractionOrder;
    private AttractionOrder order;
    private LoggedUser loggedUser;
    private String paymentMethod;
    private String amount;
    private BaseCallback<Datum> datumBaseCallback = new BaseCallback<Datum>() {
        @Override
        public void onSuccess(Datum model) {
            if (isViewAlive.get()){
                getView().setupTicketTypes(model.getTypes());
                getView().setupAddOns(model.getAddons());
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };
    private BaseCallback<AttractionOrderResponse> attractionOrderResponseBaseCallback = new BaseCallback<AttractionOrderResponse>() {
        @Override
        public void onSuccess(AttractionOrderResponse attractionOrderResponse) {
            if (isViewAlive.get()){
                getView().hideLoading();
                if (paymentMethod.equals("credit_card")){
                    loggedUser.getLoggedUser(new BaseCallback<User>() {
                        @Override
                        public void onSuccess(User model) {
                            if (isViewAlive.get()){
                                getView().setupCreditCardPayment(model,amount,attractionOrderResponse.getData().getOrder().getId());
                            }
                        }

                        @Override
                        public void onError(String message) {
                            if (isViewAlive.get()){
                                getView().showError(message);
                            }
                        }
                    });
                }else {
                    jaNavigationManager.showAttractionOrderSuccess();
                }
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

    @Inject
    public AttractionOrderPresenter(ViewTickets viewTickets, com.spade.ja.usecase.attractionorder.AttractionOrder attractionOrder,LoggedUser loggedUser) {
        this.viewTickets = viewTickets;
        this.attractionOrder = attractionOrder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void unSubscribe() {
        viewTickets.unSubscribe();
        attractionOrder.unSubscribe();
        loggedUser.unSubscribe();
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            AttractionOrder attractionOrder = extras.getParcelable(Constants.ATTRACTION_ORDER);
            this.order = attractionOrder;
            viewTickets.viewTickets(datumBaseCallback,attractionOrder.getTimeModel().getStartTime(),attractionOrder.getTimeModel().getEndTime(),attractionOrder.getTimeModel().getDate(),attractionOrder.getAttractionId());
            getView().setupOrderView(attractionOrder);

        }
    }

    @Override
    public void showPayment() {
        jaNavigationManager.popBackStack();
    }

    @Override
    public void showCalendar() {
        jaNavigationManager.openCalendarView(order.getAttractionPaymentData());
    }

    @Override
    public void order(String name, String email, String mobile, String paymentMethod, int attractionId, int totalPrice, Integer exceptionalId, Integer attractionWeekId, List<Ticket> tickets) {
        getView().showLoading();
        this.paymentMethod = paymentMethod;
        this.amount = totalPrice+"";
        attractionOrder.attractionOrder(attractionOrderResponseBaseCallback,new AttractionOrderRequest(name,email,mobile,paymentMethod,attractionId,totalPrice,exceptionalId,attractionWeekId,tickets));
    }
}
