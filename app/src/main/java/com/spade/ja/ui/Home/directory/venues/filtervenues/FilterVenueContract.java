package com.spade.ja.ui.Home.directory.venues.filtervenues;

import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 2/27/18.
 */

public class FilterVenueContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categories);
        void showResults(List<Data> venues);
        void getLatitudeAndLongitude();
    }

    interface Presenter extends Unsubscribable {
        void filterVenues(boolean weeklySuggest,List<Integer> categoryId,Double lat , Double lng);
        void showVenueInner(int id);
        void like(int id);
    }
}
