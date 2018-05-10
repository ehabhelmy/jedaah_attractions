package com.spade.ja.ui.Home.profile.my_tickets.adapter;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.attractionhistory.Ticket;
import com.spade.ja.datalayer.pojo.response.attractioninner.Addon;
import com.spade.ja.datalayer.pojo.response.history.upcoming.Datum;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.spade.ja.util.DateTimeUtils.convertJSONDateToDate;
import static com.spade.ja.util.DateTimeUtils.getDay;
import static com.spade.ja.util.DateTimeUtils.getEventDateHistory;
import static com.spade.ja.util.DateTimeUtils.getMonth;
import static com.spade.ja.util.DateTimeUtils.getWeekDay;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryEventsAdapter {

    public static List<HistoryEventsUi> convertIntoHistoryUi(List<Datum> data,boolean upOrPast) {
        ArrayList<HistoryEventsUi> historyEventsUis = new ArrayList<>();
        for (Datum datum:data) {
            HistoryEventsUi historyEventsUi = new HistoryEventsUi();
            historyEventsUi.setId(datum.getId());
            historyEventsUi.setUpOrPast(upOrPast);
            historyEventsUi.setType("event");
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

    public static List<HistoryEventsUi> convertIntoHistoryUiAttraction(List<com.spade.ja.datalayer.pojo.response.attractionhistory.Datum> data,boolean upOrPast) {
        ArrayList<HistoryEventsUi> historyEventsUis = new ArrayList<>();
        for (com.spade.ja.datalayer.pojo.response.attractionhistory.Datum datum:data) {
            HistoryEventsUi historyEventsUi = new HistoryEventsUi();
            historyEventsUi.setId(datum.getId());
            historyEventsUi.setUpOrPast(upOrPast);
            historyEventsUi.setType("attraction");
            historyEventsUi.setEventTitle(datum.getAttraction().getTitle());
            historyEventsUi.setEventImage(datum.getAttraction().getImage());
            historyEventsUi.setEventMonth(getMonth(convertJSONDateToDate(datum.getDate())));
            historyEventsUi.setEventDay(getDay(convertJSONDateToDate(datum.getDate())));
            historyEventsUi.setEventTime(getWeekDay(convertJSONDateToDate(datum.getDate())));
            historyEventsUi.setPaymentMethod(datum.getPaymentMethod());
            historyEventsUi.setOrderNumber(datum.getOrderNumber()+"");
            historyEventsUi.setOrderStatus(datum.getOrderStatus());
            int ticketsNum = 0;
            int addonsNum = 0;
            StringBuilder tickets = new StringBuilder();
            StringBuilder addons = new StringBuilder();
            for (Ticket ticket: datum.getTickets()) {
                ticketsNum = ticketsNum + ticket.getNumberOfTickets();
                tickets.append(ticket.getType());
                tickets.append("\n");
            }
            if (datum.getTickets()!= null) {
                for (Addon addon : datum.getAddons()) {
                    addonsNum++;
                    addons.append(addon.getName());
                    addons.append("\n");
                }
            }else {
                addons.append(JaApplication.getContext().getString(R.string.NOADDONS));
            }
            if (Locale.getDefault().getLanguage().equals("ar")) {
                historyEventsUi.setNumberTickets(ticketsNum +" تذكره," + addonsNum + " اضافه");
            }else {
                historyEventsUi.setNumberTickets(ticketsNum +" Tickets, " + addonsNum +" AddOns");
            }
            historyEventsUi.setTicketsPrice(((ticketsNum + addonsNum) * 100) + " SAR");
            historyEventsUi.setAddons(addons.toString());
            historyEventsUi.setTicketType(tickets.toString());
            historyEventsUis.add(historyEventsUi);
        }
        return historyEventsUis;
    }
}
