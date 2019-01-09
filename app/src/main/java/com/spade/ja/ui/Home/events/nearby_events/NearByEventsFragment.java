package com.spade.ja.ui.Home.events.nearby_events;

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

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.HomeContract;
import com.spade.ja.ui.Home.explore.adapter.EventsListAdapter;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.util.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.util.MyLocation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/15/17.
 */

public class NearByEventsFragment extends BaseFragment implements NearByEventsContract.View {

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

    @BindView(R.id.noEvents)
    RelativeLayout noEvents;


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
        if (message.equals(Constants.NO_TOKEN)) {
            showLoginRequiredError();
        } else {
            showPopUp("Server Error");
        }
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
    void openLocationSettings() {
        presenter.openLocationSettings();
    }

    @Override
    public void setupNearByEvents(List<Event> events) {
        if (events.size() > 0) {
            eventsList.setVisibility(View.VISIBLE);
            noEvents.setVisibility(View.GONE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
            eventsList.setLayoutManager(layoutManager);
            eventsList.addItemDecoration(dividerItemDecoration);
            eventsList.setItemAnimator(new DefaultItemAnimator());
            EventsListAdapter eventsListAdapter = new EventsListAdapter(true);
            eventsListAdapter.setData((ArrayList<Event>) events);
            eventsListAdapter.setOnFavouriteListener(id -> {
                //TODO : call presenter to send id of the event to the backend
                presenter.like(id);
            });
            eventsListAdapter.setOnViewListener(id -> {
                presenter.showEventInner(id);
            });
            eventsList.setAdapter(eventsListAdapter);
        }else {
            eventsList.setVisibility(View.GONE);
            noEvents.setVisibility(View.VISIBLE);
        }
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
            }else {
                MyLocation myLocation = new MyLocation();
                myLocation.getLocation(getActivity(), locationResult);
            }
        });
    }

    private MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
        @Override
        public void gotLocation(Location location) {
            if (isAdded() && isResumed() && location != null) {
                presenter.loadNearByEventsAfterLocationEnabled(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        }
    };
    @Override
    public void locationIsEnabled() {
        errorLocationContainer.setVisibility(View.GONE);
        getLatitudeAndLongitude();
    }

    @Override
    protected String getScreenTrackingName() {
        return "nearby events";
    }

}
