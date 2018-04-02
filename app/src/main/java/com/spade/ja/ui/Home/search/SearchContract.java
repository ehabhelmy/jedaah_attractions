package com.spade.ja.ui.Home.search;

import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

import java.util.List;

/**
 * Created by ehab on 3/20/18.
 */

public interface SearchContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupCategories(List<Cats> categories);
        void showResults(List<Result> results);
    }

    interface Presenter extends Unsubscribable {
        void search(String searchKeyword,List<String> types, List<Integer> categoryId);
        void showEventInner(int id);
        void showVenuesInner(int id);
        void showAttractionInner(int id);
        void like(int id);
        void venuesLike(int id);
        void attractionsLike(int id);
    }
}
