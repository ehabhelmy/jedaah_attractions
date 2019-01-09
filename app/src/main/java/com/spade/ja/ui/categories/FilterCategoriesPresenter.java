package com.spade.ja.ui.categories;

import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.map.DataConverter;
import com.spade.ja.usecase.filter.filtercats.FilterCats;
import com.spade.ja.usecase.filter.filtercatsdirectory.FilterCatsDirectory;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.usecase.like.LikeVenues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class FilterCategoriesPresenter extends BasePresenter<FilterCategoriesContract.View> implements FilterCategoriesContract.Presenter {

    private FilterCats filterCats;
    private FilterCatsDirectory filterCatsDirectory;
    private Like eventsLike;
    private LikeVenues venuesLike;
    private LikeAttractions attractionsLike;

    private BaseCallback<AllNearByResponse> baseCallback = new BaseCallback<AllNearByResponse>() {
        @Override
        public void onSuccess(AllNearByResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupData(DataConverter.convertAllNearByToView(model.getData().getResult()));
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().showError(message);
            }
        }
    };

    private BaseCallback<LikeResponse> likeResponseBaseCallback = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            // do nothing
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public FilterCategoriesPresenter(FilterCats filterCats, FilterCatsDirectory filterCatsDirectory, Like eventsLike, LikeVenues venuesLike, LikeAttractions attractionsLike) {
        this.filterCats = filterCats;
        this.filterCatsDirectory = filterCatsDirectory;
        this.eventsLike = eventsLike;
        this.venuesLike = venuesLike;
        this.attractionsLike = attractionsLike;
    }

    @Override
    public void unSubscribe() {
        filterCats.unSubscribe();
        filterCatsDirectory.unSubscribe();
    }

    public void filter(Cats cats,String type) {
        List<Integer> catsIds = new ArrayList<>();
        catsIds.add(cats.getId());
        if (type.equals(FilterCategoriesActivity.FilterCatType.EXPLORE.name())){
            filterCats.filter(catsIds,baseCallback);
        }else {
            filterCatsDirectory.filter(catsIds,baseCallback);
        }
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }

    @Override
    public void showVenuesInner(int id) {
        jaNavigationManager.showVenueInner(id);
    }

    @Override
    public void showAttractionInner(int id) {
        jaNavigationManager.showAttractionInner(id);
    }

    @Override
    public void like(int id) {
        eventsLike.like(id,likeResponseBaseCallback);
    }

    @Override
    public void venuesLike(int id) {
        venuesLike.like(id,likeResponseBaseCallback);
    }

    @Override
    public void attractionsLike(int id) {
        attractionsLike.like(id,likeResponseBaseCallback);
    }
}
