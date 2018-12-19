package com.spade.ja.ui.Home.directory.attractions.filterattractions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
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

import static com.spade.ja.ui.Home.events.filterevents.FilterEventActivity.SEARCH_CRITERIA;

/**
 * Created by ehab on 4/1/18.
 */

public class FilterAttractionActivity extends BaseActivity implements FilterAttractionContract.View {

    public static final int FILTER_ATTRACTION = 3456;
    @Inject
    FilterAttractionPresenter presenter;

    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;

    @BindView(R.id.loading_overlay_container)
    LinearLayout linearLayout;

    @BindView(R.id.clear)
    TextView clearAll;

    @BindView(R.id.weeklySugg)
    LinearLayout weeklySugg;

    @BindView(R.id.nearBy)
    LinearLayout nearBy;

    @BindView(R.id.gridview)
    RecyclerView cats;

    @BindView(R.id.filter)
    Button filter;

    @BindView(R.id.weekly_sugg_txt)
    TextView suggTxt;

    @BindView(R.id.nearby_text)
    TextView nearByTxt;

    private boolean isWeeklySelected = false;
    private boolean isNearBy = false;
    private Set<Cats> categoriesChosen = new HashSet<>();
    private FilterCategoriesAdapter filterCategoriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filter.setText(R.string.filterAttraction);
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
    void filterVenues() {
        showLoading();
        if (isNearBy){
            getLatitudeAndLongitude();
        }else {
            Intent intent = new Intent();
            intent.putExtra(SEARCH_CRITERIA, new SearchCriteria(null, null, isWeeklySelected, new ArrayList<>(categoriesChosen)));
            setResult(FILTER_ATTRACTION, intent);
            hideLoading();
            finish();
        }
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
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.venues))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }

    @Override
    public void showLoading() {
        linearLayout.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideLoading() {
        linearLayout.setVisibility(View.GONE);
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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        cats.setLayoutManager(mLayoutManager);
        cats.setItemAnimator(new DefaultItemAnimator());
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        cats.addItemDecoration(itemDecoration);
        filterCategoriesAdapter = new FilterCategoriesAdapter(this);
        filterCategoriesAdapter.setCats((ArrayList<Cats>) categories);
        cats.setAdapter(filterCategoriesAdapter);
        filterCategoriesAdapter.setOnCatSelected((cats,layout,icon,type) -> {
            if (!categoriesChosen.contains(cats)) {
                categoriesChosen.add(cats);
                layout.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect_green));
                icon.setColorFilter(ContextCompat.getColor(this,R.color.white));
                type.setTextColor(ContextCompat.getColor(this,R.color.white));
            } else {
                layout.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_rect));
                icon.setColorFilter(ContextCompat.getColor(this,R.color.colorTitle));
                type.setTextColor(ContextCompat.getColor(this,R.color.lightBlack));
                categoriesChosen.remove(cats);
            }
        });
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
                intent.putExtra(SEARCH_CRITERIA, new SearchCriteria(location1.getLatitude(), location1.getLongitude(), isWeeklySelected, new ArrayList<>(categoriesChosen)));
                setResult(FILTER_ATTRACTION, intent);
                hideLoading();
                finish();
            }
        });
    }
}
