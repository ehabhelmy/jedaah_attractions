package com.example.ehab.japroject.ui.Home.events.nearby_events;

import android.content.Context;
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
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.HomeContract;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsListAdapter;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/15/17.
 */

public class NearByEventsFragment extends BaseFragment implements NearByEventsContract.View{

    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;

    private HomeContract.View activity;

    @Inject
    NearByEventsPresenter presenter;

    @BindView(R.id.eventsList)
    RecyclerView eventsList;

    @BindView(R.id.enableLocationSettings)
    Button enableLocationSettings;

    @BindView(R.id.errorLocationContainerNew)
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
        return R.layout.fragment_events_list;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (HomeContract.View) context;
    }

    @OnClick(R.id.enableLocationSettings)
    void openLocationSettings(){
        presenter.openLocationSettings();
    }

    @Override
    public void setupNearByEvents(List<Event> events) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        eventsList.setLayoutManager(layoutManager);
        eventsList.addItemDecoration(dividerItemDecoration);
        eventsList.setItemAnimator(new DefaultItemAnimator());
        EventsListAdapter eventsListAdapter = new EventsListAdapter(true);
        eventsListAdapter.setData((ArrayList<Event>) events);
        eventsListAdapter.setOnFavouriteListener(model -> {
            Event event = (Event) model;
            //TODO : call presenter to send id of the event to the backend
        });
        eventsList.setAdapter(eventsListAdapter);
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

}
