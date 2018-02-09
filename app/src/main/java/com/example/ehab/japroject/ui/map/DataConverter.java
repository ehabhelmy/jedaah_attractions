package com.example.ehab.japroject.ui.map;

import com.example.ehab.japroject.datalayer.pojo.response.events.Datum;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 2/7/2018.
 */

public class DataConverter {

    public static ArrayList<Data> convertVenueIntoData(List<Venue> venues) {
        ArrayList<Data> dataList = new ArrayList<>();
        for (Venue venue : venues) {
            Data data = new Data();
            data.setTitle(venue.getTitle());
            data.setImage(venue.getImage());
            data.setLat(venue.getLat());
            data.setLng(venue.getLng());

            StringBuilder cat = new StringBuilder();
            for (int i = 0; i < venue.getCategories().size(); i++) {
                cat.append(venue.getCategories().get(i) != null ? venue.getCategories().get(i) : "");
                try {
                    cat.append(" | ");
                    cat.append(venue.getSubCategories().get(i));
                } catch (Exception e) {
                    cat.append("");
                } finally {
                    if (i != venue.getCategories().size() - 1) {
                        cat.append(" , ");
                    }
                }
            }
            data.setDescription(cat.toString());
            dataList.add(data);
        }
        return dataList;
    }

    public static ArrayList<Data> convertEventIntoData(List<Datum> events) {
        ArrayList<Data> dataList = new ArrayList<>();
        for (Datum event : events) {
            Data data = new Data();
            data.setTitle(event.getTitle());
            data.setImage(event.getImage());
            data.setLat(event.getLat());
            data.setLng(event.getLng());
            data.setDescription(event.getAddress());
            dataList.add(data);
        }
        return dataList;
    }

}
