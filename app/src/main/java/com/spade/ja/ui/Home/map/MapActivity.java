package com.spade.ja.ui.Home.map;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.map.adapter.DataAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements MapContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    private DataAdapter dataAdapter;
    private Location location = null;
    private ArrayList<Data> data;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.LayoutManager layoutManager;
    private LatLngBounds bounds;
    private boolean isMarkedClicked = false;

    @BindView(R.id.mapViewContainer)
    RelativeLayout mapViewContainer;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    MapPresenter presenter;
    @Inject
    FusedLocationProviderClient fusedLocationProviderClient;
    @OnClick(R.id.all)
    void allButtonClick() {
        if (isMarkedClicked){
            animateMapDecreasing();
            isMarkedClicked = false;
        }
        presenter.getAllData();
    }
    @OnClick(R.id.events)
    void eventsButtonClick() {
        if (isMarkedClicked){
            animateMapDecreasing();
            isMarkedClicked = false;
        }
        presenter.getNearByEvents();
    }
    @OnClick(R.id.venues)
    void venuesButtonClick() {
        if (isMarkedClicked){
            animateMapDecreasing();
            isMarkedClicked = false;
        }
        presenter.getNearByVenues();
    }
    @OnClick(R.id.attractions)
    void attractionsButtonClick() {
        if (isMarkedClicked){
            animateMapDecreasing();
            isMarkedClicked = false;
        }
        presenter.getNearByAttractions();
    }
    @OnClick(R.id.fab)
    void resetMap() {
        if (bounds != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 10);
            this.googleMap.moveCamera(cameraUpdate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        setupRecyclarView();
    }

    @Override
    protected void initializeDagger() {
        JaApplication application = (JaApplication) getApplicationContext();
        application.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    private void setupRecyclarView() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataAdapter = new DataAdapter();
    }

    @Override
    public void setupData(ArrayList<Data> data) {
        if (data != null && data.size() > 0) {
            this.data = data;
            if (dataAdapter.getData() == null) {
                dataAdapter.setData(data);
                recyclerView.setAdapter(dataAdapter);
            } else {
                dataAdapter.updateData(data);
            }
            drawMarkers(data);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap.setOnMarkerClickListener(this);
        presenter.getAllData();
    }

    private void drawMarkers(ArrayList<Data> dataList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Data data : dataList) {
            if (data.getLat() != null && data.getLng() != null) {
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(data.getLat()), Double.parseDouble(data.getLng()))));
                builder.include(marker.getPosition());
            }
        }
        bounds = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 10);
        googleMap.moveCamera(cameraUpdate);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;
        ArrayList<Data> temp = new ArrayList<>();
        for (Data data1 : data) {
            if (data1.getLng() != null && data1.getLat() != null) {
                if (Double.parseDouble(data1.getLat()) == latitude && Double.parseDouble(data1.getLng()) == longitude) {
                    temp.add(data1);
                }
            }
        }
        isMarkedClicked = true;
        dataAdapter.updateData(temp);
        animateMap();
        return false;
    }

    private void animateMap() {
        ValueAnimator anim = ValueAnimator.ofFloat(2, 2.5f);
        anim.addUpdateListener(valueAnimator -> {
            float val = (float) valueAnimator.getAnimatedValue();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mapViewContainer.getLayoutParams();
            layoutParams.weight = val;
            mapViewContainer.setLayoutParams(layoutParams);
        });
        anim.setDuration(1000);
        anim.start();
    }

    private void animateMapDecreasing() {
        ValueAnimator anim = ValueAnimator.ofFloat(2.5f, 2f);
        anim.addUpdateListener(valueAnimator -> {
            float val = (float) valueAnimator.getAnimatedValue();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mapViewContainer.getLayoutParams();
            layoutParams.weight = val;
            mapViewContainer.setLayoutParams(layoutParams);
        });
        anim.setDuration(1000);
        anim.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onResume();
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
    public Location getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location1) {
                if (location1 != null) MapActivity.this.location = location1;
            }
        });
        return MapActivity.this.location;
    }

}
