package com.spade.ja.ui.Home.directory.attractions.filterattractions;

import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 4/1/18.
 */

public interface FilterAttractionContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categories);
        void showResults(List<Data> attractions);
    }

    interface Presenter extends Unsubscribable {
        void filterAttractions(boolean weeklySuggest, Integer[] categoryId);
        void showAttractionInner(int id);
        void like(int id);
    }
}
