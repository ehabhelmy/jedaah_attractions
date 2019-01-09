package com.spade.ja.ui.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.Home.map.adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterCategoriesActivity extends BaseActivity implements FilterCategoriesContract.View {

    public static final String TYPE = "type";
    public static final int RETURN_CODE = 9921;
    @Inject
    FilterCategoriesPresenter presenter;

    public static final String FILTER_CATS = "filterCats";
    @BindView(R.id.category_image)
    ImageView categoryImage;
    @BindView(R.id.category_name)
    TextView categoryName;
    @BindView(R.id.category_title_container)
    RelativeLayout categoryTitleContainer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void setupData(ArrayList<Data> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.setData(data);
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.setOnItemClick(new DataAdapter.onItemClick() {
            @Override
            public void onLikeClicked(int id, String type) {
                switch (type){
                    case "event":
                        presenter.like(id);
                        break;
                    case "venue":
                        presenter.venuesLike(id);
                        break;
                    case "attraction":
                        presenter.attractionsLike(id);
                        break;
                }
            }

            @Override
            public void onCardClicked(int id, String type) {
                switch (type){
                    case "event":
                        presenter.showEventInner(id);
                        break;
                    case "venue":
                        presenter.showVenuesInner(id);
                        break;
                    case "attraction":
                        presenter.showAttractionInner(id);
                        break;
                }
            }
        });
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.searchEditText)
    public void onViewClicked() {
        setResult(RETURN_CODE);
        finish();
    }

    public enum FilterCatType {
        EXPLORE, DIRECTORY
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (getIntent() != null) {
            Cats cats = getIntent().getParcelableExtra(FILTER_CATS);
            categoryName.setText(cats.getName());
            Glide.with(categoryImage.getContext()).load(cats.getIcon()).apply(new RequestOptions().placeholder(R.drawable.ca_cafe_ic).error(R.drawable.ca_cafe_ic)).into(categoryImage);
            presenter.filter(cats,getIntent().getStringExtra(TYPE));
        }
    }

    @Override
    protected void initializeDagger() {
        DaggerMainComponent.create().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_categories_filter;
    }

    @Override
    protected String getScreenTrackingName() {
        return "Items per category";
    }
}
