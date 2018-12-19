package com.spade.ja.ui.Home.events.filterevents;

import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 2/18/18.
 */

public interface FilterEventContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categories);
        void getLatitudeAndLongitude();
    }

    interface Presenter extends Unsubscribable {

    }
}
