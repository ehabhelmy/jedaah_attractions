package com.example.ehab.japroject.ui.Home.directory.venues;

import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Venue;
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
        void setupTopVenues(List<Venue> venuesResponses);
        void setupAllVenues(List<com.example.ehab.japroject.datalayer.pojo.response.allvenues.Venue> venuesResponses);
        void addVenues(List<com.example.ehab.japroject.datalayer.pojo.response.allvenues.Venue> venues);

    }

    interface Presenter extends Unsubscribable {
         void loadMoreVenues();
    }
}
