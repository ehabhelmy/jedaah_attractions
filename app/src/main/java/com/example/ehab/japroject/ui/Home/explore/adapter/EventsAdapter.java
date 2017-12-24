package com.example.ehab.japroject.ui.Home.explore.adapter;

import com.example.ehab.japroject.datalayer.pojo.response.events.Datum;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;

import java.util.ArrayList;
import java.util.List;

import static com.example.ehab.japroject.util.DateTimeUtils.*;

/**
 * Created by ehab on 12/12/17.
 */

public class EventsAdapter {

    public static List<Event> convertIntoEventUi(List<Datum> datums){
        ArrayList<Event> events = new ArrayList<>();
        for (Datum datum:datums) {
            Event event = new Event();
            event.setEventImage(datum.getImage());
            event.setEventName(datum.getTitle());
            event.setEventAddress(datum.getAddress());
            event.setEventLikes("22 Interested - 230 Going");
            event.setEventMonth(getMonth(convertJSONDateToDate(datum.getStartDate())));
            event.setEventDay(getDay(convertJSONDateToDate(datum.getStartDate())));
            event.setEventRemaining(getDaysRemaining(convertJSONDateToDate(datum.getEndDate())));
            event.setId(datum.getId());
            events.add(event);
        }
        return events;
    }
}
