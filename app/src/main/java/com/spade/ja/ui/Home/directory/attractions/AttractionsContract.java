package com.spade.ja.ui.Home.directory.attractions;

import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by Roma on 1/14/2018.
 */

public interface AttractionsContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupTopAttractions(List<Venue> venuesResponses);
        void setupAllAttractions(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venuesResponses);
        void addAttractions(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venues);
    }

    interface Presenter extends Unsubscribable {
        void loadMoreAttractions();
        void showAttractionsInner(int id);
        void attractionLike(int id);
        void openFilterAttraction();
    }
}
