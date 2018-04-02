package com.spade.ja.ui.Home.attractioninner.AttractionOrder;

import com.spade.ja.datalayer.pojo.request.attractionorder.Ticket;
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
    }

    interface Presenter extends Unsubscribable {
        void showPayment();
        void showCalendar();
        /*{
	"name":"test",
	"email":"duha@gmail.com",
	"mobile_number":"09999999999",
	"payment_method":"cash_on_delivery",
	"attraction_id":1,
	"total":10,
	"exceptional_date_id":1,
	"attraction_week_day_id":1,
	"tickets":[
		{
			"attraction_ticket_id":1,
			"attraction_addon_id":null,
			"number":2
		},
		{
			"attraction_ticket_id":null,
			"attraction_addon_id":2,
			"number":1
		}
	]
}*/
        void order(String name, String email, String mobile, String paymentMethod, int attractionId, int totalPrice, Integer exceptionalId, Integer attractionWeekId, List<Ticket> tickets);
    }
}
