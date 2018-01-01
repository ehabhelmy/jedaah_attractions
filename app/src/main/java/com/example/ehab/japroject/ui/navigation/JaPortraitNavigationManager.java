package com.example.ehab.japroject.ui.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Home.HomeActivity;
import com.example.ehab.japroject.ui.Home.events.EventsFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.EventInnerActivity;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.EventPaymentFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;
import com.example.ehab.japroject.ui.Home.profile.ProfileFragment;
import com.example.ehab.japroject.ui.authentication.AuthenticationActivity;
import com.example.ehab.japroject.ui.authentication.login.SignInFragment;
import com.example.ehab.japroject.ui.authentication.registeration.RegisterationFragment;
import com.example.ehab.japroject.ui.authentication.socialmedia.SocialMediaFragment;
import com.example.ehab.japroject.util.Constants;

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
        replaceFragment(profileFragment,false,PROFILE,R.id.frame_layout);
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
        context.startActivity(intent);
    }

    @Override
    public void goToAuthActivity() {
        Intent intent = new Intent(context,AuthenticationActivity.class);
        context.startActivity(intent);
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
        context.startActivity(intent);
    }

    @Override
    public void openNavigationView(double lat, double lng) {
        Uri uri= Uri.parse("google.navigation:q="+lat+","+lng+"&mode=d");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        context.startActivity(intent);
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
        replaceFragment(eventPaymentFragment,false,PAYMENT,R.id.frame_layout_inner);
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
}
