package com.spade.ja.ui.Home.events.filterevents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.SearchCriteria;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.directory.venues.adapter.ItemOffsetDecoration;
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

    public static final int FILTER_EVENT = 1234;
    public static final String SEARCH_CRITERIA = "SearchCriteria";
    @Inject
    FilterEventPresenter presenter;

    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;

    @BindView(R.id.clear)
    TextView clearAll;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;

    @BindView(R.id.monthSpinner)
    AppCompatSpinner monthSpinner;

    @BindView(R.id.weeklySugg)
    LinearLayout weeklySugg;

    @BindView(R.id.weekly_sugg_txt)
    TextView suggTxt;

    @BindView(R.id.nearby_text)
    TextView nearByTxt;

    @BindView(R.id.nearBy)
    LinearLayout nearBy;

    @BindView(R.id.gridview)
    RecyclerView cats;

    @BindView(R.id.filterContainer)
    RelativeLayout filterContainer;

    private boolean isWeeklySelected = false;
    private boolean isNearBy = false;
    private int monthNumber = 1;
    private Set<Cats> categoriesChosen = new HashSet<>();
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
            suggTxt.setTextColor(ContextCompat.getColor(this,R.color.lightBlack));
            isWeeklySelected = false;
        } else {
            weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
            suggTxt.setTextColor(ContextCompat.getColor(this,R.color.white));
            isWeeklySelected = true;
        }
    }

    @OnClick(R.id.nearBy)
    void selectNearBy() {
        if (isNearBy) {
            nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
            nearByTxt.setTextColor(ContextCompat.getColor(this,R.color.lightBlack));
            isNearBy = false;
        } else {
            nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
            nearByTxt.setTextColor(ContextCompat.getColor(this,R.color.white));
            isNearBy = true;
        }
    }


    @OnClick(R.id.filter)
    void filterEvents() {
        showLoading();
        if (isNearBy) {
            getLatitudeAndLongitude();
        } else {
            Intent intent = new Intent();
            intent.putExtra(SEARCH_CRITERIA, new SearchCriteria(monthNumber, null, null, isWeeklySelected, new ArrayList<>(categoriesChosen)));
            setResult(FILTER_EVENT, intent);
            hideLoading();
            finish();
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
                Intent intent = new Intent();
                intent.putExtra(SEARCH_CRITERIA, new SearchCriteria(monthNumber, location1.getLatitude(), location1.getLongitude(), isWeeklySelected, new ArrayList<>(categoriesChosen)));
                setResult(FILTER_EVENT, intent);
                hideLoading();
                finish();
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
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        cats.setLayoutManager(mLayoutManager);
        cats.setItemAnimator(new DefaultItemAnimator());
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        cats.addItemDecoration(itemDecoration);
        filterCategoriesAdapter = new FilterCategoriesAdapter(this);
        filterCategoriesAdapter.setCats((ArrayList<Cats>) categories);
        cats.setAdapter(filterCategoriesAdapter);
        filterCategoriesAdapter.setOnCatSelected((cats, layout, icon, type) -> {
            if (!categoriesChosen.contains(cats)) {
                categoriesChosen.add(cats);
                layout.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
                icon.setColorFilter(ContextCompat.getColor(this, R.color.white));
                type.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                layout.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
                icon.setColorFilter(ContextCompat.getColor(this, R.color.colorTitle));
                type.setTextColor(ContextCompat.getColor(this, R.color.lightBlack));
                categoriesChosen.remove(cats);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0){
            monthNumber = 0;
        }else {
            monthNumber = i + 1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @OnClick(R.id.clear)
    void clear() {
        reset();
    }

    private void reset() {
        categoriesChosen.clear();
        isWeeklySelected = false;
        isNearBy = false;
        weeklySugg.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
        nearBy.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
        cats.setAdapter(null);
        cats.setAdapter(filterCategoriesAdapter);
        filterCategoriesAdapter.notifyDataSetChanged();
        scrollView.post(() -> scrollView.fullScroll(scrollView.FOCUS_UP));
    }
}
