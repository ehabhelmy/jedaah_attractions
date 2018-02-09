package com.spade.ja.ui.navigation;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;

import java.lang.ref.WeakReference;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class JaNavigationManager {

    protected static Context context;

    protected FragmentManager fragmentManager;

    protected static JaNavigationManager jaNavigationManager;

    protected WeakReference<BaseActivity> currentActivity;

    public static final String EXPLORE = "explore";

    public static final String SIGNIN = "sign";

    public static final String EVENTS = "events";

    public static final String SOCIAL = "social";

    public static final String REGISTERATION = "registeration";

    public static final String EVENT_DETAILS = "eventDetails";

    public static final String VENUE_DETAILS = "venueDetails";

    public static final String PAYMENT = "payment";

    public static final String ORDER = "order";

    public static final String PROFILE = "profile";

    public static final String DIRECTORY = "directory";

    public static final String ORDER_SUCCESS = "orderSuccess";

    public static final int LOCATION_SETTINGS = 2;

    public static final int EVENT_INNER = 3;

    public static final int VENUE_INNER = 5;


    public static JaNavigationManager getInstance(){
        if (jaNavigationManager == null){
            context = JaApplication.getContext();
            // TODO : check if it is tablet then return instance of tablet navigation
            jaNavigationManager = new JaPortraitNavigationManager();
        }
        return jaNavigationManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public abstract void showExploreScreen();

    public abstract void showEventsScreen();

    public abstract void showProfileScreen();

    public abstract void showDirectoryScreen();

    public abstract void showLocationSettings();

    public abstract void showSocialMediaScreen();

    public abstract void showSignInScreen();

    public abstract void goToHomeActivity();

    public abstract void goToAuthActivity(String token);

    public abstract void showRegisterationScreen();

    public abstract void showEventDetails(int id);

    public abstract void showEventInner(int id);

    public abstract void showVenueDetails(int anInt);

    public abstract void showVenueInner(int id);

    public abstract void openNavigationView(double lat, double lng);

    public abstract void openPaymentView(PaymentData paymentData);

    public abstract void showOrderView(EventOrder eventOrder);

    public abstract void goBackToOrderView();

    public abstract void showEventOrderSuccess();

    public abstract void openEditActivity(Data profileData);

    public abstract void showMapActivity();

    protected void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag, int frame){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(frame,fragment,tag);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected void addFragment(BaseFragment fragment, boolean addToBackStack, String tag, int frame){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.add(frame,fragment,tag);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        fragmentTransaction.commit();
    }

    public  <F extends BaseFragment> F getCurrentFragmentOnHome(){
        return (F) fragmentManager.findFragmentById(R.id.frame_layout);
    }

    public  <F extends BaseFragment> F getCurrentFragmentOnAuth(){
        return (F) fragmentManager.findFragmentById(R.id.frame_layout_auth);
    }
    public  <F extends BaseFragment> F getCurrentFragmentOnInner(){
        return (F) fragmentManager.findFragmentById(R.id.frame_layout_inner);
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<BaseActivity>(currentActivity);
    }

}
