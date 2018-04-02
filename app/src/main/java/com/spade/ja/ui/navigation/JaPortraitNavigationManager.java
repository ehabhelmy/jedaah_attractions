package com.spade.ja.ui.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Home.HomeActivity;
import com.spade.ja.ui.Home.attractioninner.AttractionInnerActivity;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.AttractionOrderFragment;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.attractiondetails.AttractionDetailsFragment;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.AttractionPaymentFragment;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.calendar.CalendarFragment;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;
import com.spade.ja.ui.Home.directory.DirectoryFragment;
import com.spade.ja.ui.Home.directory.venues.filtervenues.FilterVenueActivity;
import com.spade.ja.ui.Home.events.EventsFragment;
import com.spade.ja.ui.Home.events.filterevents.FilterEventActivity;
import com.spade.ja.ui.Home.eventsinner.EventInnerActivity;
import com.spade.ja.ui.Home.eventsinner.eventbuy.EventPaymentFragment;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;
import com.spade.ja.ui.Home.explore.ExploreFragment;
import com.spade.ja.ui.Home.map.MapActivity;
import com.spade.ja.ui.Home.profile.ProfileFragment;
import com.spade.ja.ui.Home.profile.edit.EditActivity;
import com.spade.ja.ui.Home.profile.settings.SettingsActivity;
import com.spade.ja.ui.Home.profile.settings.contactus.ContactUsFragment;
import com.spade.ja.ui.Home.profile.settings.settingsinner.SettingsInnerFragment;
import com.spade.ja.ui.Home.search.SearchFragment;
import com.spade.ja.ui.Home.venueinner.VenueInnerActivity;
import com.spade.ja.ui.Home.venueinner.venuedetails.VenueDetailsFragment;
import com.spade.ja.ui.authentication.AuthenticationActivity;
import com.spade.ja.ui.authentication.login.SignInFragment;
import com.spade.ja.ui.authentication.registeration.RegisterationFragment;
import com.spade.ja.ui.authentication.socialmedia.SocialMediaFragment;
import com.spade.ja.ui.splash.SplashActivity;
import com.spade.ja.util.Constants;

/**
 * Created by ehab on 12/1/17.
 */

public class JaPortraitNavigationManager extends JaNavigationManager {


    private static final String ATTRACTION_PAYMENT = "attraction_payment";
    private static final String ATTRACTION_ORDER = "attractionOrder";
    private static final String SEARCH = "search";

    @Override
    public void showExploreScreen() {
        ExploreFragment exploreFragment = (ExploreFragment) fragmentManager.findFragmentByTag(EXPLORE);
        if (exploreFragment == null) {
            exploreFragment = new ExploreFragment();
        }
        replaceFragment(exploreFragment,false,EXPLORE, R.id.frame_layout);
    }

    @Override
    public void showSearch() {
        SearchFragment searchFragment = (SearchFragment) fragmentManager.findFragmentByTag(SEARCH);
        if (searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        replaceFragment(searchFragment,false,SEARCH, R.id.frame_layout);
    }

    @Override
    public void showEventsScreen() {
        EventsFragment eventsFragment = (EventsFragment) fragmentManager.findFragmentByTag(EVENTS);
        if (eventsFragment == null) {
            eventsFragment = new EventsFragment();
        }
        replaceFragment(eventsFragment,false,EVENTS,R.id.frame_layout);
    }

    @Override
    public void showProfileScreen() {
        ProfileFragment profileFragment = (ProfileFragment) fragmentManager.findFragmentByTag(PROFILE);
        if (profileFragment == null) {
            profileFragment = new ProfileFragment();
        }
        replaceFragment(profileFragment,false,PROFILE, R.id.frame_layout);
    }

    @Override
    public void showDirectoryScreen() {
        DirectoryFragment directoryFragment = (DirectoryFragment) fragmentManager.findFragmentByTag(DIRECTORY);
        if (directoryFragment == null) {
            directoryFragment = new DirectoryFragment();
        }
        replaceFragment(directoryFragment,false,DIRECTORY,R.id.frame_layout);
    }

    @Override
    public void showLocationSettings() {
        getCurrentActivity().startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),LOCATION_SETTINGS);
    }

    @Override
    public void openEmail() {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, "spade@jeddahAttractions.com");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack");
//        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
//        startActivity(intent);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","spade@jeddahAttractions.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        getCurrentActivity().startActivityForResult(Intent.createChooser(emailIntent, "Send email..."),7);
    }

    @Override
    public void showSocialMediaScreen() {
        SocialMediaFragment socialMediaFragment = (SocialMediaFragment) fragmentManager.findFragmentByTag(SOCIAL);
        if (socialMediaFragment == null) {
            socialMediaFragment = new SocialMediaFragment();
        }
        replaceFragment(socialMediaFragment,false,SOCIAL,R.id.frame_layout_auth);
    }

    @Override
    public void showSignInScreen() {
        SignInFragment signInFragment = (SignInFragment) fragmentManager.findFragmentByTag(SIGNIN);
        if (signInFragment == null) {
            signInFragment = new SignInFragment();
        }
        replaceFragment(signInFragment,true,SIGNIN,R.id.frame_layout_auth);
    }

    @Override
    public void goToHomeActivity() {
        Intent intent = new Intent(context,HomeActivity.class);
        getCurrentActivity().finish();
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void goToAuthActivity(String token) {
        Intent intent = new Intent(context,AuthenticationActivity.class);
        if (token == null) {
            if (getCurrentActivity() instanceof HomeActivity || getCurrentActivity() instanceof SplashActivity) {
                getCurrentActivity().finish();
            }
        }
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void showRegisterationScreen() {
        RegisterationFragment registerationFragment = (RegisterationFragment) fragmentManager.findFragmentByTag(REGISTERATION);
        if (registerationFragment == null) {
            registerationFragment = new RegisterationFragment();
        }
        replaceFragment(registerationFragment,true,REGISTERATION,R.id.frame_layout_auth);
    }

    @Override
    public void showEventDetails(int id) {
        EventInnerFragment eventInnerFragment = (EventInnerFragment) fragmentManager.findFragmentByTag(EVENT_DETAILS);
        if (eventInnerFragment == null) {
            eventInnerFragment = new EventInnerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.EVENT_ID,id);
            eventInnerFragment.setArguments(bundle);
        }
        replaceFragment(eventInnerFragment,false,EVENT_DETAILS,R.id.frame_layout_inner);
    }

    @Override
    public void showEventInner(int id) {
        Intent intent = new Intent(context,EventInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EVENT_ID,id);
        intent.putExtras(bundle);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.EVENT_INNER);
    }

    @Override
    public void showVenueDetails(int anInt) {
        VenueDetailsFragment venueDetailsFragment = (VenueDetailsFragment) fragmentManager.findFragmentByTag(VENUE_DETAILS);
        if (venueDetailsFragment == null) {
            venueDetailsFragment = new VenueDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.VENUE_ID,anInt);
            venueDetailsFragment.setArguments(bundle);
        }
        replaceFragment(venueDetailsFragment,false,VENUE_DETAILS,R.id.frame_layout_inner_venue);
    }

    @Override
    public void showAttractionDetails(int anInt) {
        AttractionDetailsFragment attractionDetailsFragment = (AttractionDetailsFragment) fragmentManager.findFragmentByTag(ATTRACTION_DETAILS);
        if (attractionDetailsFragment == null) {
            attractionDetailsFragment = new AttractionDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.ATTRACTION_ID,anInt);
            attractionDetailsFragment.setArguments(bundle);
        }
        replaceFragment(attractionDetailsFragment,false,ATTRACTION_DETAILS,R.id.frame_layout_inner_attraction);
    }

    @Override
    public void showVenueInner(int id) {
        Intent intent = new Intent(context,VenueInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.VENUE_ID,id);
        intent.putExtras(bundle);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.VENUE_INNER);
    }

    @Override
    public void showAttractionInner(int id) {
        Intent intent = new Intent(context,AttractionInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ATTRACTION_ID,id);
        intent.putExtras(bundle);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.ATTRACTION_INNER);
    }

    @Override
    public void openNavigationView(double lat, double lng) {
        Uri uri= Uri.parse("google.navigation:q="+lat+","+lng+"&mode=d");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void openPaymentView(PaymentData paymentData) {
        EventPaymentFragment eventPaymentFragment = (EventPaymentFragment) fragmentManager.findFragmentByTag(PAYMENT);
        if (eventPaymentFragment == null) {
            eventPaymentFragment = new EventPaymentFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.EVENT_PAYMENT,paymentData);
            eventPaymentFragment.setArguments(bundle);
        }
        replaceFragment(eventPaymentFragment,true,PAYMENT,R.id.frame_layout_inner);
    }

    @Override
    public void showOrderView(EventOrder eventOrder) {
        EventOrderFragment eventOrderFragment = (EventOrderFragment) fragmentManager.findFragmentByTag(ORDER);
        if (eventOrderFragment == null) {
            eventOrderFragment = new EventOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.EVENT_ORDER,eventOrder);
            eventOrderFragment.setArguments(bundle);
        }
        replaceFragment(eventOrderFragment,true,ORDER,R.id.frame_layout_inner);
    }

    @Override
    public void goBackToOrderView() {
        fragmentManager.popBackStack();
    }

    @Override
    public void showEventOrderSuccess() {
        EventOrderSuccessFragment eventOrderSuccessFragment = (EventOrderSuccessFragment) fragmentManager.findFragmentByTag(ORDER_SUCCESS);
        if (eventOrderSuccessFragment == null) {
            eventOrderSuccessFragment = new EventOrderSuccessFragment();
        }
        replaceFragment(eventOrderSuccessFragment,true,ORDER_SUCCESS,R.id.frame_layout_inner);
    }

    @Override
    public void showAttractionOrderSuccess() {
        EventOrderSuccessFragment eventOrderSuccessFragment = (EventOrderSuccessFragment) fragmentManager.findFragmentByTag(ORDER_SUCCESS);
        if (eventOrderSuccessFragment == null) {
            eventOrderSuccessFragment = new EventOrderSuccessFragment();
        }
        replaceFragment(eventOrderSuccessFragment,true,ORDER_SUCCESS,R.id.frame_layout_inner_attraction);
    }

    @Override
    public void openEditActivity(Data profileData) {
        Intent intent = new Intent(context,EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PROFILE_DATA,profileData);
        intent.putExtras(bundle);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.MAP);
    }

    @Override
    public void openSettings() {
        Intent intent = new Intent(context,SettingsActivity.class);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.MAP);
    }

    @Override
    public void showMapActivity() {
        Intent intent = new Intent(getCurrentActivity(), MapActivity.class);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.MAP);
    }

    @Override
    public void openFilterEvents() {
        Intent intent = new Intent(context,FilterEventActivity.class);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.MAP);
    }

    @Override
    public void openFilterVenues() {
        Intent intent = new Intent(context,FilterVenueActivity.class);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.MAP);
    }

    @Override
    public void showSettingsInner() {
        SettingsInnerFragment fragment = (SettingsInnerFragment) fragmentManager.findFragmentByTag(SETTINGS);
        if (fragment == null) {
            fragment = new SettingsInnerFragment();
        }
        replaceFragment(fragment,false,SETTINGS,R.id.frame_layout_settings);
    }

    @Override
    public void openContactUs() {
        ContactUsFragment fragment = (ContactUsFragment) fragmentManager.findFragmentByTag(CONTACT_US);
        if (fragment == null) {
            fragment = new ContactUsFragment();
        }
        replaceFragment(fragment,true,CONTACT_US,R.id.frame_layout_settings);
    }

    @Override
    public void openCalendarView(AttractionPaymentData attractionPaymentData) {
        CalendarFragment calendarFragment = (CalendarFragment) fragmentManager.findFragmentByTag(CALENDAR);
        if (calendarFragment == null) {
            calendarFragment = new CalendarFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.CALENDAR_PAYMENT,attractionPaymentData);
            calendarFragment.setArguments(bundle);
        }
        replaceFragment(calendarFragment,true,CALENDAR,R.id.frame_layout_inner_attraction);
    }

    @Override
    public void openAttractionPaymentView(AttractionPaymentModel attractionPaymentModel) {
        AttractionPaymentFragment fragment = (AttractionPaymentFragment) fragmentManager.findFragmentByTag(ATTRACTION_PAYMENT);
        if (fragment == null) {
            fragment = new AttractionPaymentFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ATTRACTION_PAYMENT,attractionPaymentModel);
            fragment.setArguments(bundle);
        }
        replaceFragment(fragment,true,ATTRACTION_PAYMENT,R.id.frame_layout_inner_attraction);
    }

    @Override
    public void showAttractionOrderView(AttractionOrder attractionOrder) {
        AttractionOrderFragment fragment = (AttractionOrderFragment) fragmentManager.findFragmentByTag(ATTRACTION_ORDER);
        if (fragment == null) {
            fragment = new AttractionOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ATTRACTION_ORDER,attractionOrder);
            fragment.setArguments(bundle);
        }
        replaceFragment(fragment,true,ATTRACTION_ORDER,R.id.frame_layout_inner_attraction);
    }

    @Override
    public void popBackStack() {
        getCurrentActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void restartApp() {
        Intent i = new Intent(context, SplashActivity.class);
        getCurrentActivity().startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        System.exit(0);
    }

    @Override
    public void showEventInnerAsNew(int id) {
        Intent intent = new Intent(context,EventInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EVENT_ID,id);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void showVenueInnerAsNew(int id) {
        Intent intent = new Intent(context,VenueInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.VENUE_ID,id);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void showAttractionInnerAsNew(int id) {
        Intent intent = new Intent(context,AttractionInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ATTRACTION_ID,id);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
