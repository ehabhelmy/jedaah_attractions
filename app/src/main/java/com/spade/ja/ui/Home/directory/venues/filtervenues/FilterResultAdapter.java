package com.spade.ja.ui.Home.directory.venues.filtervenues;

import com.spade.ja.datalayer.pojo.response.filter.venues.Result;
import com.spade.ja.datalayer.pojo.response.venues.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehab on 2/27/18.
 */

public class FilterResultAdapter {

    public static List<Venue> convertResultIntoVenue(List<Result> results) {
        ArrayList<Venue> venues = new ArrayList<>();
        for (Result result: results) {
            Venue venue = new Venue();
            venue.setCategories(result.getCategories());
            venue.setSubCategories(result.getSubCategories());
            venue.setCreatedAt(result.getCreatedAt());
            venue.setId(result.getId());
            venue.setImage(result.getImage());
            venue.setIsBrand(result.getIsBrand());
            venue.setIsFeatured(result.getIsFeatured());
            venue.setIsSponsored(result.getIsSponsored());
            venue.setLat(result.getLat());
            venue.setLng(result.getLng());
            venue.setNumberOfLikes(result.getNumberOfLikes());
            venue.setTitle(result.getTitle());
            venue.setType(result.getType());
        }
        return venues;
    }
}
