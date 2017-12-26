package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.Data;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.pojo.EventDetails;

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
        ArrayList<String> days = new ArrayList<>();
        days.add("Firday, 13 nov 3pm : 5pm");
        days.add("Firday, 13 nov 3pm : 5pm");
        days.add("Firday, 13 nov 3pm : 5pm");
        eventDetails.setEventDateDays(days);
        eventDetails.setImageURLS((ArrayList<String>) data.getGallery());
        return eventDetails;
    }
}
