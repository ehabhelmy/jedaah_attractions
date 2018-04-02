package com.spade.ja.ui.Home.search;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.categories.Categories;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.usecase.like.LikeVenues;
import com.spade.ja.usecase.search.Search;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ehab on 3/20/18.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private Like eventsLike;
    private LikeVenues venuesLike;
    private LikeAttractions attractionsLike;
    private Search search;
    private Categories categories;


    @Inject
    public SearchPresenter(Like eventsLike, LikeVenues venuesLike, LikeAttractions attractionsLike, Search search,Categories categories) {
        this.eventsLike = eventsLike;
        this.venuesLike = venuesLike;
        this.attractionsLike = attractionsLike;
        this.search = search;
        this.categories = categories;
    }

    private BaseCallback<Category> categoryBaseCallback = new BaseCallback<Category>() {
        @Override
        public void onSuccess(Category model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupCategories(model.getData());
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

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        categories.getCategories(categoryBaseCallback,true);
    }


    private BaseCallback<AllNearByResponse> allNearByResponseBaseCallback = new BaseCallback<AllNearByResponse>() {
        @Override
        public void onSuccess(AllNearByResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().showResults(model.getData().getResult());
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

    @Override
    public void unSubscribe() {
        eventsLike.unSubscribe();
        venuesLike.unSubscribe();
        attractionsLike.unSubscribe();
        search.unSubscribe();
        categories.unSubscribe();
    }

    @Override
    public void search(String searchKeyword, List<String> types, List<Integer> categoryId) {
        search.search(searchKeyword,types,categoryId,allNearByResponseBaseCallback);
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
