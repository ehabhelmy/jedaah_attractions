package com.example.ehab.japroject.ui.Home.directory.venues;

import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
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
    }

    interface Presenter extends Unsubscribable {

    }
}
