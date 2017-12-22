package com.example.ehab.japroject.ui.Home.eventsinner.adapter;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.Data;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.datalayer.pojo.response.events.Datum;
import com.example.ehab.japroject.ui.Home.eventsinner.pojo.EventDetails;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import static com.example.ehab.japroject.util.DateTimeUtils.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.ehab.japroject.util.DateTimeUtils.convertJSONDateToDate;
import static com.example.ehab.japroject.util.DateTimeUtils.getDay;
import static com.example.ehab.japroject.util.DateTimeUtils.getDaysRemaining;
import static com.example.ehab.japroject.util.DateTimeUtils.getMonth;

/**
 * Created by ehab on 12/20/17.
 */

public class EventDetailsAdapter {

    public static EventDetails convertIntoEventDetailsUi(Data data) {
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
        StringBuilder cat = null;
        for (int i = 0 ; i < data.getCategories().size() ; i++ ) {
            cat.append(data.getCategories().get(i).getName());
            cat.append("|");
            cat.append(data.getSubCategories().get(i));
        }
        eventDetails.setCategoriesText(cat.toString());
        eventDetails.setIsliked(true);
        ArrayList<String> days = new ArrayList<>();
        days.add("Firday, 13 nov 3pm : 5pm");
        days.add("Firday, 13 nov 3pm : 5pm");
        days.add("Firday, 13 nov 3pm : 5pm");
        eventDetails.setEventDateDays(days);
        return eventDetails;
    }
}
