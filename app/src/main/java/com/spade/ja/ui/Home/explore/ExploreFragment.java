package com.spade.ja.ui.Home.explore;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.HomeContract;
import com.spade.ja.ui.Home.explore.adapter.CategoryListAdapter;
import com.spade.ja.ui.Home.explore.adapter.EventsListAdapter;
import com.spade.ja.ui.Home.explore.adapter.VenuesListAdapter;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.ui.widget.CircularImageView;
import com.spade.ja.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/11/17.
 */

public class ExploreFragment extends BaseFragment implements ExploreContract.View {

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

    @BindView(R.id.topAttractions)
    RecyclerView topAttractions;

    @BindView(R.id.nearByAttractions)
    RecyclerView nearByAttractions;

    @BindView(R.id.attractionsenableLocationSettings)
    Button attractionsEnableLocationSettings;

    @BindView(R.id.attractionserrorLocationContainerNew)
    RelativeLayout attractionsErrorLocationContainer;

    @BindView(R.id.exploreNearBy)
    LinearLayout exploreNearBy;

    @BindView(R.id.venuesNearBy)
    LinearLayout venuesNearBy;

    @BindView(R.id.attractionsNearBy)
    LinearLayout attractionsNearBy;

    @BindView(R.id.noTopEvents)
    RelativeLayout noTopEvents;

    @BindView(R.id.noCatogories)
    RelativeLayout noCatogories;

    @BindView(R.id.noNearEvents)
    RelativeLayout noNearEvents;

    @BindView(R.id.noTopVenues)
    RelativeLayout noTopVenues;

    @BindView(R.id.noTopAttractions)
    RelativeLayout noTopAttractions;

    @BindView(R.id.noNearVenues)
    RelativeLayout noNearByVenues;

    @BindView(R.id.noNearAttractions)
    RelativeLayout noNearAttractions;

    @BindView(R.id.fab)
    CircularImageView fab;

    private HomeContract.View activity;
    private boolean locationCheck;

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

    @OnClick(R.id.fab)
    void openMapActivity() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showDialog();
        } else {
            jaNavigationManager.showMapActivity();
        }

    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage(" please enable location ")
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
    public void setupTopVenues(List<Venue> data) {
        if (data.size() > 0) {
            setupVenuesRecyclerView(topVenues, data);
        }else {
            noTopVenues.setVisibility(View.VISIBLE);
            topVenues.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupNearbyVenues(List<Venue> data) {
        if (data.size() > 0) {
            setupVenuesRecyclerView(nearByVenues, data);
        }else {
            noNearByVenues.setVisibility(View.VISIBLE);
            nearByVenues.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupTopAttractions(List<Venue> data) {
        if (data.size() > 0) {
            setupAttractionsRecyclerView(topAttractions, data);
        }else {
            noTopAttractions.setVisibility(View.VISIBLE);
            topAttractions.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupNearbyAttractions(List<Venue> data) {
        if (data.size() > 0) {
            setupAttractionsRecyclerView(nearByAttractions, data);
        }else {
            noNearAttractions.setVisibility(View.VISIBLE);
            nearByAttractions.setVisibility(View.GONE);
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

    private void setupVenuesRecyclerView(RecyclerView recyclerView, List<Venue> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        VenuesListAdapter venuesListAdapter = new VenuesListAdapter(false);
        venuesListAdapter.setData((ArrayList<Venue>) data);
        venuesListAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.venueLike(id);
        });
        venuesListAdapter.setOnViewListener(id -> {
            presenter.showVenueInner(id);
        });
        recyclerView.setAdapter(venuesListAdapter);
    }

    private void setupAttractionsRecyclerView(RecyclerView recyclerView, List<Venue> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        VenuesListAdapter venuesListAdapter = new VenuesListAdapter(false);
        venuesListAdapter.setData((ArrayList<Venue>) data);
        venuesListAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.attractionsLike(id);
        });
        venuesListAdapter.setOnViewListener(id -> {
            presenter.showAttractionsInner(id);
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
    public void onResume() {
        super.onResume();
        if (locationCheck) {
            fab.setVisibility(View.VISIBLE);
        }
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
                presenter.loadNearByAttractionsAfterLocationEnabled(new LatLng(location1.getLatitude(), location1.getLongitude()));
            }
        });
    }

    @Override
    public void locationIsEnabled() {
        errorLocationContainer.setVisibility(View.GONE);
        venueErrorLocationContainer.setVisibility(View.GONE);
        attractionsErrorLocationContainer.setVisibility(View.GONE);
        getLatitudeAndLongitude();
    }

    @OnClick(R.id.enableLocationSettings)
    void openLocationSettings() {
        presenter.openLocationSettings();
    }

    @OnClick(R.id.venueenableLocationSettings)
    void openLocationVenuesSettings() {
        presenter.openLocationSettings();
    }

    @OnClick(R.id.attractionsenableLocationSettings)
    void openLocationAttractionsSettings() {
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
        } else {
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

    @Override
    public void hideNearByAttractions() {
        attractionsNearBy.setVisibility(View.GONE);
    }

    @Override
    public void setLocationPermission(boolean check) {
        locationCheck = check;
    }
}
