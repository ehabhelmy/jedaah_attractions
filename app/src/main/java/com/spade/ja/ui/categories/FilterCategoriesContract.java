package com.spade.ja.ui.categories;


import com.spade.ja.datalayer.pojo.response.allnearby.Data;
import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

import java.util.ArrayList;
import java.util.List;

public interface FilterCategoriesContract {

    interface View extends BaseView, ProgressView, ErrorView {
        void setupData(ArrayList<com.spade.ja.ui.Home.map.Data> venues);
    }

    interface Presenter extends Unsubscribable {
        void showEventInner(int id);
        void showVenuesInner(int id);
        void showAttractionInner(int id);
        void like(int id);
        void venuesLike(int id);
        void attractionsLike(int id);
    }
}
