package com.example.ehab.japroject.ui.Home.explore;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.HomeContract;
import com.example.ehab.japroject.ui.Home.explore.adapter.TopEventsListAdapter;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

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

    @BindView(R.id.nearByEvents)
    RecyclerView nearByEvents;

    @BindView(R.id.enableLocationSettings)
    Button enableLocationSettings;

    @BindView(R.id.errorLocationContainer)
    RelativeLayout errorLocationContainer;

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
    public void showError() {

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        topEvents.setLayoutManager(layoutManager);
        topEvents.addItemDecoration(dividerItemDecoration);
        topEvents.setItemAnimator(new DefaultItemAnimator());
        TopEventsListAdapter topEventsListAdapter = new TopEventsListAdapter();
        topEventsListAdapter.setData((ArrayList<Event>) events);
        topEventsListAdapter.setOnFavouriteListener(model -> {
            Event event = (Event) model;
            //TODO : call presenter to send id of the event to the backend
        });
        topEvents.setAdapter(topEventsListAdapter);
    }

    @Override
    public void setupNearbyEvents(List<Event> events) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        nearByEvents.setLayoutManager(layoutManager);
        nearByEvents.addItemDecoration(dividerItemDecoration);
        nearByEvents.setItemAnimator(new DefaultItemAnimator());
        TopEventsListAdapter topEventsListAdapter = new TopEventsListAdapter();
        topEventsListAdapter.setData((ArrayList<Event>) events);
        topEventsListAdapter.setOnFavouriteListener(model -> {
            Event event = (Event) model;
            //TODO : call presenter to send id of the event to the backend
        });
        nearByEvents.setAdapter(topEventsListAdapter);
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

    public void locationIsEnabled(){
        errorLocationContainer.setVisibility(View.GONE);
        getLatitudeAndLongitude();
    }
    @OnClick(R.id.enableLocationSettings)
    void openLocationSettings(){
        presenter.openLocationSettings();
    }

}
