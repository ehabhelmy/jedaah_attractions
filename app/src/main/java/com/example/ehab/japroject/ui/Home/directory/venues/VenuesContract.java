package com.example.ehab.japroject.ui.Home.directory.venues;

import com.example.ehab.japroject.datalayer.pojo.response.allvenues.Venue;
import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Datum;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Roma on 1/14/2018.
 */

public interface VenuesContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categoryList);
        void setupTopVenues(List<Datum> venuesResponses);
        void setupAllVenues(List<Venue> venuesResponses);

    }

    interface Presenter extends Unsubscribable {

    }
}
