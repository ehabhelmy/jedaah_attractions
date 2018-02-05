package com.spade.ja.ui.Home.explore.adapter;

import com.spade.ja.datalayer.pojo.response.events.Datum;
import com.spade.ja.ui.Home.explore.pojo.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.spade.ja.util.DateTimeUtils.*;

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
            if (Locale.getDefault().getLanguage().equals("ar")){
                event.setEventLikes((datum.getInterested() != 0 ? datum.getInterested()+ " مهتم -":"")+(datum.getGoing() !=0 ? datum.getGoing()+" ذاهب":""));
            }else {
                event.setEventLikes((datum.getInterested() != 0 ? datum.getInterested()+ " Interested -":"")+(datum.getGoing() !=0 ? datum.getGoing()+" Going":""));
            }
            event.setEventMonth(getMonth(convertJSONDateToDate(datum.getStartDate())));
            event.setEventDay(getDay(convertJSONDateToDate(datum.getStartDate())));
            event.setEventRemaining(getDaysRemaining(convertJSONDateToDate(datum.getEndDate() != null ? datum.getEndDate():datum.getStartDate())));
            event.setId(datum.getId());
            event.setLiked(datum.isLiked());
            events.add(event);
        }
        return events;
    }

    public static List<Event> convertIntoEventUiAll(List<com.spade.ja.datalayer.pojo.response.allevents.Event> datums){
        ArrayList<Event> events = new ArrayList<>();
        for (com.spade.ja.datalayer.pojo.response.allevents.Event datum :datums) {
            Event event = new Event();
            event.setEventImage(datum.getImage());
            event.setEventName(datum.getTitle());
            event.setLiked(datum.isLiked());
            event.setEventAddress(datum.getAddress());
            if (Locale.getDefault().getLanguage().equals("ar")){
                event.setEventLikes(datum.getInterested()+ " مهتم -"+datum.getGoing()+" ذاهب");
            }else {
                event.setEventLikes(datum.getInterested()+ " Interested -"+datum.getGoing()+" Going");
            }
            event.setEventMonth(getMonth(convertJSONDateToDate(datum.getStartDate())));
            event.setEventDay(getDay(convertJSONDateToDate(datum.getStartDate())));
            event.setEventRemaining(getDaysRemaining(convertJSONDateToDate(datum.getEndDate() != null ? datum.getEndDate() : datum.getStartDate())));
            event.setId(datum.getId());
            events.add(event);
        }
        return events;
    }
}
