package com.example.ehab.japroject.ui.Home.explore.adapter;

import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import static com.example.ehab.japroject.util.DateTimeUtils.*;

/**
 * Created by ehab on 12/12/17.
 */

public class EventsAdapter {

    public static Event convertIntoEventUi(Datum datum){
        Event event = new Event();
        event.setEventImage(datum.getImages().get(0));
        event.setEventName(datum.getTitle());
        event.setEventAddress(datum.getAddress());
        event.setEventLikes("22 Interested - 230 Going");
        event.setEventMonth(getMonth(convertJSONDateToDate(datum.getDate())));
        event.setEventDay(getDay(convertJSONDateToDate(datum.getDate())));
        event.setEventRemaining(getDaysRemaining(convertJSONDateToDate(datum.getDate())));
        return event;
    }
}
