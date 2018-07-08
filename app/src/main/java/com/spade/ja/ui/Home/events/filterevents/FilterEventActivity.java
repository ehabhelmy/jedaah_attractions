package com.spade.ja.ui.Home.events.filterevents;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseActivity;
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
 * Created by ehab on 2/18/18.
 */

public class FilterEventActivity extends BaseActivity implements FilterEventContract.View, AdapterView.OnItemSelectedListener {

    @Inject
    FilterEventPresenter presenter;

    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;

    @BindView(R.id.clear)
    TextView clearAll;

    @BindView(R.id.monthSpinner)
    AppCompatSpinner monthSpinner;

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
    private boolean isNearBy = false;
    private int monthNumber = 1;
    private Set<Integer> categoriesChosen = new HashSet<>();
    private FilterCategoriesAdapter filterCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMonthSpinner();
    }

    private void setupMonthSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, R.layout.dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(this);
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

    @OnClick(R.id.nearBy)
    void selectNearBy() {
        if (isNearBy) {
            nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
            isNearBy = false;
        } else {
            nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
            isNearBy = true;
        }
    }


    @OnClick(R.id.filter)
    void filterEvents() {
        if (isNearBy) {
            getLatitudeAndLongitude();
        }else {
            List<Integer> cats = new ArrayList<>(categoriesChosen);
            presenter.filterEvents(isWeeklySelected,cats , monthNumber,null,null);

        }
    }

    @Override
    public void getLatitudeAndLongitude() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location1 -> {
            if (location1 != null) {
                List<Integer> cats = new ArrayList<>(categoriesChosen);
                presenter.filterEvents(isWeeklySelected,cats , monthNumber,location1.getLatitude(),location1.getLongitude());
            }
        });
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.events))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
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
        return R.layout.filter_layout;
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
                name.setTextColor(ContextCompat.getColor(FilterEventActivity.this,R.color.white));
                icon.setColorFilter(ContextCompat.getColor(FilterEventActivity.this,R.color.white));
                view.setBackground(ContextCompat.getDrawable(FilterEventActivity.this, R.drawable.tag_rect_green));
            } else {
                view.setBackground(ContextCompat.getDrawable(FilterEventActivity.this, R.drawable.tag_rect));
                name.setTextColor(ContextCompat.getColor(FilterEventActivity.this,R.color.lightBlack));
                icon.setColorFilter(ContextCompat.getColor(FilterEventActivity.this,R.color.colorTitle));
                categoriesChosen.remove(cats.getId());
            }
        });
    }

    @Override
    public void showResults(List<Data> events) {
        filterContainer.setVisibility(View.GONE);
        filterResults.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));
        filterResults.setLayoutManager(layoutManager);
        filterResults.setItemAnimator(new DefaultItemAnimator());
        filterResults.addItemDecoration(dividerItemDecoration);
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.setData((ArrayList<Data>) events);
        dataAdapter.setOnItemClick(new DataAdapter.onItemClick() {
            @Override
            public void onLikeClicked(int id, String type) {
                presenter.like(id);
            }

            @Override
            public void onCardClicked(int id, String type) {
                presenter.showEventInner(id);
            }
        });
        filterResults.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        monthNumber = i + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        isNearBy = false;
        monthNumber = 1;
        weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
        nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
        cats.setAdapter(null);
        cats.setAdapter(filterCategoriesAdapter);
        filterCategoriesAdapter.notifyDataSetChanged();
    }
}
