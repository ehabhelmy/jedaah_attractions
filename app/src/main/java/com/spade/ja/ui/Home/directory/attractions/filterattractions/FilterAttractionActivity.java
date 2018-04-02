package com.spade.ja.ui.Home.directory.attractions.filterattractions;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.directory.venues.filtervenues.FilterVenueActivity;
import com.spade.ja.ui.Home.directory.venues.filtervenues.FilterVenuePresenter;
import com.spade.ja.ui.Home.events.filterevents.adapter.FilterCategoriesAdapter;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.Home.map.adapter.DataAdapter;
import com.spade.ja.ui.widget.StaticGridView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 4/1/18.
 */

public class FilterAttractionActivity extends BaseActivity implements FilterAttractionContract.View {

    @Inject
    FilterAttractionPresenter presenter;

    @BindView(R.id.clear)
    TextView clearAll;

    @BindView(R.id.weeklySugg)
    LinearLayout weeklySugg;

    @BindView(R.id.nearBy)
    LinearLayout nearBy;

    @BindView(R.id.tags)
    StaticGridView cats;

    @BindView(R.id.filterResult)
    RecyclerView filterResults;

    @BindView(R.id.filterContainer)
    RelativeLayout filterContainer;

    private boolean isWeeklySelected = false;
    private Set<Integer> categoriesChosen = new HashSet<>();
    private FilterCategoriesAdapter filterCategoriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.weeklySugg)
    void selectWeeklySugg() {
        if (isWeeklySelected) {
            weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
            isWeeklySelected = false;
        } else {
            weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
            isWeeklySelected = true;
        }
    }

    @OnClick(R.id.filter)
    void filterAttractions() {
        presenter.filterAttractions(isWeeklySelected, categoriesChosen.toArray(new Integer[categoriesChosen.size()]));
    }

    @OnClick(R.id.clear)
    void clear() {
        if (filterContainer.getVisibility() == View.VISIBLE){
            reset();
        }else {
            reset();
            filterContainer.setVisibility(View.VISIBLE);
            filterResults.setVisibility(View.GONE);
        }
    }

    private void reset() {
        categoriesChosen.clear();
        isWeeklySelected = false;
        weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
        cats.setAdapter(null);
        cats.setAdapter(filterCategoriesAdapter);
        filterCategoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Venues")
                .setMessage(message)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }

    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter_venues;
    }

    @Override
    public void setupCategories(List<Cats> categories) {
        filterCategoriesAdapter = new FilterCategoriesAdapter(this);
        filterCategoriesAdapter.setCats((ArrayList<Cats>) categories);
        cats.setAdapter(filterCategoriesAdapter);
        cats.setOnItemClickListener((adapterView, view, i, l) -> {
            Cats cats = (Cats) adapterView.getItemAtPosition(i);
            ImageView icon = view.findViewById(R.id.tagIcon);
            TextView name = view.findViewById(R.id.tagType);
            if (!categoriesChosen.contains(cats.getId())) {
                categoriesChosen.add(cats.getId());
                name.setTextColor(ContextCompat.getColor(FilterAttractionActivity.this,R.color.white));
                icon.setColorFilter(ContextCompat.getColor(FilterAttractionActivity.this,R.color.white));
                view.setBackground(ContextCompat.getDrawable(FilterAttractionActivity.this, R.drawable.tag_rect_green));
            } else {
                view.setBackground(ContextCompat.getDrawable(FilterAttractionActivity.this, R.drawable.tag_rect));
                name.setTextColor(ContextCompat.getColor(FilterAttractionActivity.this,R.color.lightBlack));
                icon.setColorFilter(ContextCompat.getColor(FilterAttractionActivity.this,R.color.colorTitle));
                categoriesChosen.remove(cats.getId());
            }
        });
    }

    @Override
    public void showResults(List<Data> venues) {
        filterContainer.setVisibility(View.GONE);
        filterResults.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));
        filterResults.setLayoutManager(layoutManager);
        filterResults.setItemAnimator(new DefaultItemAnimator());
        filterResults.addItemDecoration(dividerItemDecoration);
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.setData((ArrayList<Data>) venues);
        dataAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.like(id);
        });
        dataAdapter.setOnViewListener(id -> {
            //presenter.showVenueInner(id);
        });
        filterResults.setAdapter(dataAdapter);
    }
}
