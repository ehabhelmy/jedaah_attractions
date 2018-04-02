package com.spade.ja.ui.Home.map;

import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.datalayer.pojo.response.events.Datum;
import com.spade.ja.datalayer.pojo.response.venues.Venue;

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
            data.setType(venue.getType());
            data.setTitle(venue.getTitle());
            data.setImage(venue.getImage());
            data.setLat(venue.getLat());
            data.setLng(venue.getLng());
            data.setLiked(venue.getIsLiked());
            data.setId(venue.getId());

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

    public static ArrayList<Data> convertAllNearByToView(List<Result> allData) {
        ArrayList<Data> dataList = new ArrayList<>();
        for (Result result : allData) {
            Data data = new Data();
            data.setType(result.getType());
            data.setTitle(result.getTitle());
            data.setImage(result.getImage());
            data.setLat(result.getLat());
            data.setLng(result.getLng());
            data.setLiked(result.getIsLiked());
            data.setId(result.getId());

            StringBuilder cat = new StringBuilder();
            for (int i = 0; i < result.getCategories().size(); i++) {
                cat.append(result.getCategories().get(i) != null ? result.getCategories().get(i) : "");
                try {
                    cat.append(" | ");
                    cat.append(result.getSubCategories().get(i));
                } catch (Exception e) {
                    cat.append("");
                } finally {
                    if (i != result.getCategories().size() - 1) {
                        cat.append(" , ");
                    }
                }
            }
            if (result.getType().equals("event")){
                data.setDescription(result.getAddress());
            }else {
                data.setDescription(cat.toString());
            }
            dataList.add(data);
        }
        return dataList;
    }

    public static ArrayList<Data> convertEventIntoData(List<Datum> events) {
        ArrayList<Data> dataList = new ArrayList<>();
        for (Datum event : events) {
            Data data = new Data();
            data.setType("Event");
            data.setTitle(event.getTitle());
            data.setImage(event.getImage());
            data.setLat(event.getLat());
            data.setLng(event.getLng());
            data.setDescription(event.getAddress());
            data.setLiked(event.isLiked());
            data.setId(event.getId());
            dataList.add(data);
        }
        return dataList;
    }

    public static List<Data> convertIntoEventUiFilter(List<com.spade.ja.datalayer.pojo.response.filter.events.Result> datums){
        ArrayList<Data> dataList = new ArrayList<>();
        for (com.spade.ja.datalayer.pojo.response.filter.events.Result event : datums) {
            Data data = new Data();
            data.setTitle(event.getTitle());
            data.setImage(event.getImage());
            data.setLat(event.getLat());
            data.setLng(event.getLng());
            data.setDescription(event.getAddress());
            data.setLiked(event.getIsLiked());
            data.setId(event.getId());
            dataList.add(data);
        }
        return dataList;
    }

    public static List<Data> convertIntoVenueUiFilter(List<com.spade.ja.datalayer.pojo.response.filter.venues.Result> datums){
        ArrayList<Data> dataList = new ArrayList<>();
        for (com.spade.ja.datalayer.pojo.response.filter.venues.Result venue : datums) {
            Data data = new Data();
            data.setTitle(venue.getTitle());
            data.setImage(venue.getImage());
            data.setLat(venue.getLat());
            data.setLng(venue.getLng());
            data.setLiked(venue.getIsLiked());
            data.setId(venue.getId());

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

}
