package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.Data;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventDay;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.OrderEventDay;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.pojo.EventDetails;

import static com.example.ehab.japroject.util.DateTimeUtils.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.ehab.japroject.util.DateTimeUtils.convertJSONDateToDate;
import static com.example.ehab.japroject.util.DateTimeUtils.getDay;
import static com.example.ehab.japroject.util.DateTimeUtils.getDaysRemaining;
import static com.example.ehab.japroject.util.DateTimeUtils.getMonth;
import static com.example.ehab.japroject.util.DateTimeUtils.getEventDays;
import static com.example.ehab.japroject.util.DateTimeUtils.getEventDateOrder;

/**
 * Created by ehab on 12/20/17.
 */

public class EventDetailsAdapter {

    public static EventDetails convertIntoEventDetailsUi(List<Data> data1) {
        Data data = data1.get(0);
        EventDetails eventDetails = new EventDetails();
        eventDetails.setEventTitle(data.getTitle());
        eventDetails.setInterested(data.getInterested()+"");
        eventDetails.setEventPrice(data.getStartPrice()+" - "+data.getEndPrice());
        eventDetails.setEventMonth(getMonth(convertJSONDateToDate(data.getStartDate())));
        eventDetails.setEventDay(getDay(convertJSONDateToDate(data.getStartDate())));
        eventDetails.setEventDatRemaining(getDaysRemaining(convertJSONDateToDate(data.getStartDate())));
        eventDetails.setEventAddress(data.getAddress());
        eventDetails.setEventDatetitle(getEventDateTitle(data.getStartDate(),data.getEndDate()));
        eventDetails.setSocialMedia((ArrayList<SocialMedium>) data.getSocialMedia());
        eventDetails.setEventTags((ArrayList<EventTag>) data.getEventTags());
        eventDetails.setEventDescription(data.getDescription());
        eventDetails.setLatitude(Double.parseDouble(data.getLat()));
        eventDetails.setLongitude(Double.parseDouble(data.getLng()));
        StringBuilder cat = new StringBuilder();
        for (int i = 0 ; i < data.getCategories().size() ; i++ ) {
            cat.append(data.getCategories().get(i).getName());
            cat.append(" | ");
            cat.append(data.getSubCategories().get(i));
            cat.append(",");
        }
        eventDetails.setCategoriesText(cat.toString());
        eventDetails.setIsliked(data.getIsLiked());
        ArrayList<EventDay> eventDays = (ArrayList<EventDay>) data.getEventDays();
        ArrayList<String> days = new ArrayList<>();
        for (EventDay eventDay: eventDays) {
            days.add(getEventDays(eventDay));
        }
        eventDetails.setEventDateDays(days);
        eventDetails.setImageURLS((ArrayList<String>) data.getGallery());
        return eventDetails;
    }

    public static PaymentData setupPaymentPojo(List<Data> data1){
        Data data = data1.get(0);
        PaymentData paymentData = new PaymentData();
        paymentData.setVipPrice(data.getEndPrice());
        paymentData.setRegularPrice(data.getStartPrice());
        ArrayList<OrderEventDay> orderEventDays = new ArrayList<>();
        for (EventDay eventDay: data.getEventDays()) {
            OrderEventDay orderEventDay = new OrderEventDay();
            orderEventDay.setMonth(getMonth(convertJSONDateToDate(eventDay.getStartDate())));
            orderEventDay.setDay(getDay(convertJSONDateToDate(eventDay.getStartDate())));
            orderEventDay.setTime(getEventDateOrder(eventDay));
            orderEventDays.add(orderEventDay);
        }
        paymentData.setEventDateDays(orderEventDays);
        paymentData.setEventTitle(data.getTitle());
        if (data.getCreditCard() == 1 ) {
            paymentData.setCreditCard(true);
        }else {
            paymentData.setCreditCard(false);
        }
        if (data.getCashOnDelivery() == 1 ) {
            paymentData.setCash(true);
        }else {
            paymentData.setCash(false);
        }
        if (data.getPayLater() == 1 ) {
            paymentData.setPayLater(true);
        }else {
            paymentData.setPayLater(false);
        }
        paymentData.setCashTickets(data.getMaxOfCashTickets());
        paymentData.setPayLaterTickets(data.getMaxOfPayLaterTickets());
        if (data.getNational() != null) {
            paymentData.setNationalRequired(true);
        }else {
            paymentData.setNationalRequired(false);
        }
        return paymentData;
    }
}
