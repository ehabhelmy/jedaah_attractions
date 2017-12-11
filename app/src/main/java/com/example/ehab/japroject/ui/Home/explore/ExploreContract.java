package com.example.ehab.japroject.ui.Home.explore;

import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 12/11/17.
 */

public interface ExploreContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupTopEvents(List<Datum> events);
    }

    interface Presenter extends Unsubscribable{

    }
}
