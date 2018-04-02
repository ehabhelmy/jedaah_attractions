package com.spade.ja.ui.Home.directory.venues;

import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Roma on 1/14/2018.
 */

public interface VenuesContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categoryList);
        void setupTopVenues(List<Venue> venuesResponses);
        void setupAllVenues(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venuesResponses);
        void addVenues(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venues);

    }

    interface Presenter extends Unsubscribable {
        void loadMoreVenues();
        void showVenueInner(int id);
        void venueLike(int id);
        void openFilterVenues();
    }
}
