package com.example.ehab.japroject.ui.Home.explore;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.CategoryListAdapter;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsListAdapter;
import com.example.ehab.japroject.ui.Home.HomeContract;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.util.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/11/17.
 */

public class ExploreFragment extends BaseFragment implements ExploreContract.View {

    private HomeContract.View activity;

    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    ExplorePresenter presenter;

    @BindView(R.id.topEvents)
    RecyclerView topEvents;

    @BindView(R.id.categories)
    RecyclerView categories;

    @BindView(R.id.nearByEvents)
    RecyclerView nearByEvents;

    @BindView(R.id.enableLocationSettings)
    Button enableLocationSettings;

    @BindView(R.id.errorLocationContainer)
    RelativeLayout errorLocationContainer;

    @BindView(R.id.exploreNearBy)
    LinearLayout exploreNearBy;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    public void showError(String message) {
        if (message.equals(Constants.NO_TOKEN)) {
            showLoginRequiredError();
        } else {
            showPopUp("Server Error");
        }
    }

    @OnClick(R.id.events)
    void openEventListing(){
        activity.openEventsList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HomeContract.View) context;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setupTopEvents(List<Event> events) {
        setupRecyclerView(topEvents,events);
    }

    @Override
    public void setupNearbyEvents(List<Event> events) {
        setupRecyclerView(nearByEvents,events);
    }

    private void setupRecyclerView(RecyclerView recyclerView,List<Event> events) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EventsListAdapter eventsListAdapter = new EventsListAdapter(false);
        eventsListAdapter.setData((ArrayList<Event>) events);
        eventsListAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.like(id);
        });
        eventsListAdapter.setOnViewListener(id -> {
            presenter.showEventInner(id);
        });
        recyclerView.setAdapter(eventsListAdapter);
    }

    @Override
    public boolean isLocationPermissionGranted() {
        return activity.isLocationPermissionGranted();
    }

    @Override
    public boolean isLocationEnabled() {
        return activity.isLocationServicesEnabled();
    }

    @Override
    public void showErrorLocationNotEnabled() {
        errorLocationContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void getLatitudeAndLongitude() {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location1 -> {
          presenter.loadNearByEventsAfterLocationEnabled(new LatLng(location1.getLatitude(),location1.getLongitude()));
        });
    }

    @Override
    public void locationIsEnabled(){
        errorLocationContainer.setVisibility(View.GONE);
        getLatitudeAndLongitude();
    }
    @OnClick(R.id.enableLocationSettings)
    void openLocationSettings(){
        presenter.openLocationSettings();
    }


    @Override
    public void setupCategories(List<Cats> categoryList) {
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.category_divider));
        categories.setLayoutManager(layoutManager);
        categories.addItemDecoration(dividerItemDecoration);
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter();
        categoryListAdapter.setData((ArrayList<Cats>) categoryList);
        categories.setAdapter(categoryListAdapter);
    }

    public void hideNearByEvents() {
        exploreNearBy.setVisibility(View.GONE);
    }


}
