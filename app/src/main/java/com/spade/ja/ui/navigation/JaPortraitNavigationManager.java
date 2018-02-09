package com.spade.ja.ui.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Home.HomeActivity;
import com.spade.ja.ui.Home.directory.DirectoryFragment;
import com.spade.ja.ui.Home.events.EventsFragment;
import com.spade.ja.ui.Home.eventsinner.EventInnerActivity;
import com.spade.ja.ui.Home.eventsinner.eventbuy.EventPaymentFragment;
import com.spade.ja.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;
import com.spade.ja.ui.Home.explore.ExploreFragment;
import com.spade.ja.ui.Home.profile.ProfileFragment;
import com.spade.ja.ui.Home.profile.edit.EditActivity;
import com.spade.ja.ui.Home.venueinner.VenueInnerActivity;
import com.spade.ja.ui.Home.venueinner.venuedetails.VenueDetailsFragment;
import com.spade.ja.ui.authentication.AuthenticationActivity;
import com.spade.ja.ui.authentication.login.SignInFragment;
import com.spade.ja.ui.authentication.registeration.RegisterationFragment;
import com.spade.ja.ui.authentication.socialmedia.SocialMediaFragment;
import com.spade.ja.ui.splash.SplashActivity;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/1/17.
 */

public class JaPortraitNavigationManager extends JaNavigationManager {


    @Override
    public void showExploreScreen() {
        ExploreFragment exploreFragment = (ExploreFragment) fragmentManager.findFragmentByTag(EXPLORE);
        if (exploreFragment == null) {
            exploreFragment = new ExploreFragment();
        }
        replaceFragment(exploreFragment,false,EXPLORE, R.id.frame_layout);
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
        replaceFragment(eventInnerFragment,true,EVENT_DETAILS,R.id.frame_layout_inner);
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
    public void showVenueInner(int id) {
        Intent intent = new Intent(context,VenueInnerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.VENUE_ID,id);
        intent.putExtras(bundle);
        getCurrentActivity().startActivityForResult(intent,JaNavigationManager.VENUE_INNER);
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
    public void openEditActivity(Data profileData) {
        Intent intent = new Intent(context,EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PROFILE_DATA,profileData);
        intent.putExtras(bundle);
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void showMapActivity() {
        Intent intent = new Intent(getCurrentActivity(), MapActivity.class);
        getCurrentActivity().startActivity(intent);
    }
}
