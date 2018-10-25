package com.spade.ja.ui.Home.attractioninner.AttractionOrder;

import com.spade.ja.datalayer.pojo.request.attractionorder.Ticket;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.datalayer.pojo.response.viewtickets.Addon;
import com.spade.ja.datalayer.pojo.response.viewtickets.Type;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 3/14/18.
 */

public interface AttractionOrderContract {

    interface View extends BaseView,ProgressView,ErrorView {
        void setupOrderView(AttractionOrder eventOrder);
        void setupTicketTypes(List<Type> tickets);
        void setupAddOns(List<Addon> addons);
        void setupCreditCardPayment(User user, String amount, int id);
    }

    interface Presenter extends Unsubscribable {
        void showPayment();
        void showCalendar();
        void order(String name, String email, String mobile, String paymentMethod, int attractionId, int totalPrice, Integer exceptionalId, Integer attractionWeekId, List<Ticket> tickets);
    }
}
