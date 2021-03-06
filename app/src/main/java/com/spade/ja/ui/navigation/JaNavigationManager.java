package com.spade.ja.ui.navigation;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.ui.categories.FilterCategoriesActivity;

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

    public static final String FILTER_VENUES = "filterVenues";

    public static final String ATTRACTION_DETAILS = "attractionDetails";

    public static final String SETTINGS = "settings";

    public static final String CONTACT_US = "contactus";

    public static final String CALENDAR = "calendar";

    public static final String RESETCODE = "resetCode";

    public static final String COMEFROM = "comeFrom";

    public static final String ATTRACTION_PAYMENT = "attraction_payment";

    public static final String ATTRACTION_ORDER = "attractionOrder";

    public static final String SEARCH = "search";

    public static final String ABOUT = "asdas";

    public static final String FULLPHOTO = "photo";

    public static final String CODE = "code" ;

    public static final String RESET = "reset" ;

    public static final String VERFICATION = "verfication";

    public static final int LOCATION_SETTINGS = 2;

    public static final int EVENT_INNER = 3;

    public static final int VENUE_INNER = 5;

    public static final int ATTRACTION_INNER = 9;

    public static final int MAP = 6;


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

    public abstract void showSearch();

    public abstract void showEventsScreen();

    public abstract void showEventsScreenAsNew();

    public abstract void showProfileScreen();

    public abstract void showDirectoryScreen();

    public abstract void showDirectoryScreen(int type);

    public abstract void showVenuesScreenAsNew();

    public abstract void showAttractionScreenAsNew();

    public abstract void showLocationSettings();

    public abstract void openEmail();

    public abstract void showSocialMediaScreen();

    public abstract void showSignInScreen();

    public abstract void goToHomeActivity();

    public abstract void goToAuthActivity(String token);

    public abstract void goToWalkthrough();

    public abstract void showRegisterationScreen();

    public abstract void showEventDetails(int id);

    public abstract void showEventInner(int id);

    public abstract void showVenueDetails(int anInt);

    public abstract void showAttractionDetails(int anInt);

    public abstract void showVenueInner(int id);

    public abstract void showAttractionInner(int id);

    public abstract void openNavigationView(double lat, double lng);

    public abstract void openPaymentView(PaymentData paymentData);

    public abstract void showOrderView(EventOrder eventOrder);

    public abstract void goBackToOrderView();

    public abstract void showEventOrderSuccess();

    public abstract void showAttractionOrderSuccess();

    public abstract void openEditActivity(Data profileData);

    public abstract void openSettings();

    public abstract void showMapActivity();

    public abstract void openFilterEvents();

    public abstract void openFilterVenues();

    public abstract void openFilterAttraction();

    public abstract void showSettingsInner();

    public abstract void openFeedback();

    public abstract void openCalendarView(AttractionPaymentData attractionPaymentData);

    public abstract void openAttractionPaymentView(AttractionPaymentModel attractionPaymentModel);

    public abstract void showAttractionOrderView(AttractionOrder attractionOrder);

    public abstract void popBackStack();

    public abstract void restartApp();

    public abstract void showEventInnerAsNew(int id);

    public abstract void showVenueInnerAsNew(int id);

    public abstract void showAttractionInnerAsNew(int id);

    public abstract void openAboutScreen();

    public abstract void showFullScreenPhoto(String imageUrl, String title);

    public abstract void goToResetCode();

    public abstract void goToResetPassword(String forgetCode);

    public abstract void showPhoneVerfication(EventOrder eventOrder);

    public abstract void showPhoneVerficationAttraction(AttractionOrder attractionOrder);

    protected void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag, int frame){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(frame,fragment,tag);
        //fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        if (tag.equals(EXPLORE)){
            fragmentTransaction.commitNow();
        }else {
            fragmentTransaction.commitAllowingStateLoss();
        }
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
    public  <F extends BaseFragment> F getCurrentFragmentOnInnerAttraction(){
        return (F) fragmentManager.findFragmentById(R.id.frame_layout_inner_attraction);
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<BaseActivity>(currentActivity);
    }

    public abstract void openContactUs();

    public abstract void showFilterCategories(Cats cats, FilterCategoriesActivity.FilterCatType type);
}
