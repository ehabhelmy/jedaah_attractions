package com.spade.ja.ui.Home.profile.my_tickets.adapter;

import com.spade.ja.datalayer.pojo.response.history.upcoming.Datum;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.spade.ja.util.DateTimeUtils.convertJSONDateToDate;
import static com.spade.ja.util.DateTimeUtils.getDay;
import static com.spade.ja.util.DateTimeUtils.getEventDateHistory;
import static com.spade.ja.util.DateTimeUtils.getMonth;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryEventsAdapter {

    public static List<HistoryEventsUi> convertIntoHistoryUi(List<Datum> data) {
        ArrayList<HistoryEventsUi> historyEventsUis = new ArrayList<>();
        for (Datum datum:data) {
            HistoryEventsUi historyEventsUi = new HistoryEventsUi();
            historyEventsUi.setEventTitle(datum.getEvent().getTitle());
            historyEventsUi.setEventImage(datum.getEvent().getImage());
            historyEventsUi.setEventMonth(getMonth(convertJSONDateToDate(datum.getTicketDate().getDate())));
            historyEventsUi.setEventDay(getDay(convertJSONDateToDate(datum.getTicketDate().getDate())));
            historyEventsUi.setEventTime(getEventDateHistory(datum.getTicketDate()));
            historyEventsUi.setPaymentMethod(datum.getPaymentMethod());
            historyEventsUi.setTicketType(datum.getEventTicket().getType());
            historyEventsUi.setOrderNumber(datum.getOrderNumber()+"");
            historyEventsUi.setOrderStatus(datum.getOrderStatus());
            if (Locale.getDefault().getLanguage().equals("ar")) {
                historyEventsUi.setNumberTickets(datum.getNumberOfTickets()+" تذكره");
            }else {
                historyEventsUi.setNumberTickets(datum.getNumberOfTickets()+" Tickets");
            }
            historyEventsUi.setTicketsPrice(datum.getNumberOfTickets().intValue() * Integer.parseInt(datum.getEventTicket().getPrice()) + " SAR");
            historyEventsUis.add(historyEventsUi);
        }
        return historyEventsUis;
    }
}
