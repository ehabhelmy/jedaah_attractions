package com.spade.ja.ui.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.explore.ExploreContract;
import com.spade.ja.ui.Home.explore.ExploreFragment;
import com.spade.ja.ui.navigation.JaNavigationManager;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/2/17.
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;
    @Inject
    HomePresenter presenter;
    @BindView(R.id.frame_layout)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    private boolean locationPermission = false;
    private boolean locationdEnabled = false;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.explore :
                    presenter.showExplore();
                    break;
                case R.id.search :
                    presenter.showSearch();
                    break;
                case R.id.events :
                    presenter.showEvents();
                    break;
                case R.id.profile :
                    presenter.showProfile();
                    break;
                case R.id.directory :
                    presenter.showDirectory();
            }
            return true;
        }
    };

    @SuppressLint("RestrictedApi")
    public void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BottomNav", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BottomNav", "Unable to change value of shift mode", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        removeShiftMode(bottomNavigationView);
        getLocationPermission();
        checkLocationEnabled();
        presenter.showExplore();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (locationPermission) {
            if (presenter.getCurrentFragmentOnHome() instanceof ExploreContract.View) {
                ExploreContract.View exploreFragment = (ExploreContract.View) presenter.getCurrentFragmentOnHome();
                exploreFragment.setLocationPermission(true);
            }
        }
    }

    private void checkLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationdEnabled = true;
        }
    }

    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     *
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
        }
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //the permission is already granted just check it when you need it
            locationPermission = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        JaNavigationManager.getInstance().setFragmentManager(getSupportFragmentManager());
        if (requestCode == JaNavigationManager.LOCATION_SETTINGS) {
            if (resultCode == 0) {
                presenter.locationIsEnabled();
            }
        }
//        if (requestCode == JaNavigationManager.EVENT_INNER) {
//            bottomNavigationView.setSelectedItemId(R.id.events);
//            presenter.showEvents();
//        }
//        if (requestCode == JaNavigationManager.MAP) {
//            bottomNavigationView.setSelectedItemId(R.id.explore);
//            presenter.showExplore();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationPermission = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermission = true;
                    presenter.locationIsEnabled();
                } else {
                    presenter.hideNearByEvents();
                }
                break;

            case MY_PERMISSIONS_REQUEST_SMS_RECEIVE:
                System.out.println("Asdasdasda");
                break;
        }
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
        return R.layout.activity_home;
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
    public boolean isLocationPermissionGranted() {
        return locationPermission;
    }

    @Override
    public boolean isLocationServicesEnabled() {
        return locationdEnabled;
    }

    @Override
    public void openEventsList() {
        bottomNavigationView.setSelectedItemId(R.id.events);
        presenter.showEvents();
    }
}
