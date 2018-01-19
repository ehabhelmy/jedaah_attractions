package com.example.ehab.japroject.ui.Home.explore;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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
import com.example.ehab.japroject.datalayer.pojo.response.venues.Datum;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.CategoryListAdapter;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsListAdapter;
import com.example.ehab.japroject.ui.Home.HomeContract;
import com.example.ehab.japroject.ui.Home.explore.adapter.VenuesListAdapter;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.usecase.registeration.Registeration;
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

    @BindView(R.id.errorLocationContainerNew)
    RelativeLayout errorLocationContainer;

    @BindView(R.id.topVenues)
    RecyclerView topVenues;

    @BindView(R.id.nearByVenues)
    RecyclerView nearByVenues;

    @BindView(R.id.venueenableLocationSettings)
    Button venueEnableLocationSettings;

    @BindView(R.id.venueerrorLocationContainerNew)
    RelativeLayout venueErrorLocationContainer;

    @BindView(R.id.exploreNearBy)
    LinearLayout exploreNearBy;

    @BindView(R.id.venuesNearBy)
    LinearLayout venuesNearBy;

    @BindView(R.id.noTopEvents)
    RelativeLayout noTopEvents;

    @BindView(R.id.noCatogories)
    RelativeLayout noCatogories;

    @BindView(R.id.noNearEvents)
    RelativeLayout noNearEvents;

    @BindView(R.id.noTopVenues)
    RelativeLayout noTopVenues;

    @BindView(R.id.noNearVenues)
    RelativeLayout noNearByVenues;

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
    void openEventListing() {
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
        if (events.size() > 0) {
            setupRecyclerView(topEvents, events);
        }else {
            noTopEvents.setVisibility(View.VISIBLE);
            topEvents.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupNearbyEvents(List<Event> events) {
        if (events.size() > 0) {
            setupRecyclerView(nearByEvents, events);
        }else {
            noNearEvents.setVisibility(View.VISIBLE);
            nearByEvents.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupTopVenues(List<Datum> data) {
        if (data.size() > 0) {
            setupVenuesRecyclerView(topVenues, data);
        }else {
            noTopVenues.setVisibility(View.VISIBLE);
            topVenues.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupNearbyVenues(List<Datum> data) {
        if (data.size() > 0) {
            setupVenuesRecyclerView(nearByVenues, data);
        }else {
            noNearByVenues.setVisibility(View.VISIBLE);
            nearByVenues.setVisibility(View.GONE);
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Event> events) {
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

    private void setupVenuesRecyclerView(RecyclerView recyclerView, List<Datum> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        VenuesListAdapter venuesListAdapter = new VenuesListAdapter();
        venuesListAdapter.setData((ArrayList<Datum>) data);
        venuesListAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.venueLike(id);
        });
        venuesListAdapter.setOnViewListener(id -> {
            //presenter.showEventInner(id);
        });
        recyclerView.setAdapter(venuesListAdapter);
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
        venueErrorLocationContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void getLatitudeAndLongitude() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                presenter.loadNearByEventsAfterLocationEnabled(new LatLng(location1.getLatitude(), location1.getLongitude()));
                presenter.loadNearByVenuesAfterLocationEnabled(new LatLng(location1.getLatitude(), location1.getLongitude()));
            }
        });
    }

    @Override
    public void locationIsEnabled(){
        errorLocationContainer.setVisibility(View.GONE);
        venueErrorLocationContainer.setVisibility(View.GONE);
        getLatitudeAndLongitude();
    }
    @OnClick(R.id.enableLocationSettings)
    void openLocationSettings(){
        presenter.openLocationSettings();
    }

    @OnClick(R.id.venueenableLocationSettings)
    void openLocationVenuesSettings(){
        presenter.openLocationSettings();
    }


    @Override
    public void setupCategories(List<Cats> categoryList) {
        if (categoryList.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.category_divider));
            categories.setLayoutManager(layoutManager);
            categories.addItemDecoration(dividerItemDecoration);
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter();
            categoryListAdapter.setData((ArrayList<Cats>) categoryList);
            categories.setAdapter(categoryListAdapter);
        }else {
            categories.setVisibility(View.GONE);
            noCatogories.setVisibility(View.VISIBLE);
        }
    }

    public void hideNearByEvents() {
        exploreNearBy.setVisibility(View.GONE);
    }

    @Override
    public void hideNearByVenues() {
        venuesNearBy.setVisibility(View.GONE);
    }
}
