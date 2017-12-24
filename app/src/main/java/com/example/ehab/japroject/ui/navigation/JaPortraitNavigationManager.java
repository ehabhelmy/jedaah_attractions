package com.example.ehab.japroject.ui.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Home.HomeActivity;
import com.example.ehab.japroject.ui.Home.events.EventsFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.EventInnerActivity;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;
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
        addFragment(exploreFragment,false,EXPLORE, R.id.frame_layout);
    }

    @Override
    public void showEventsScreen() {
        EventsFragment eventsFragment = (EventsFragment) fragmentManager.findFragmentByTag(EVENTS);
        if (eventsFragment == null) {
            eventsFragment = new EventsFragment();
        }
        addFragment(eventsFragment,false,EVENTS,R.id.frame_layout);
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
        addFragment(socialMediaFragment,false,SOCIAL,R.id.frame_layout_auth);
    }

    @Override
    public void showSignInScreen() {
        SignInFragment signInFragment = (SignInFragment) fragmentManager.findFragmentByTag(SIGNIN);
        if (signInFragment == null) {
            signInFragment = new SignInFragment();
        }
        addFragment(signInFragment,false,SIGNIN,R.id.frame_layout_auth);
    }

    @Override
    public void goToHomeActivity() {
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showRegisterationScreen() {
        RegisterationFragment registerationFragment = (RegisterationFragment) fragmentManager.findFragmentByTag(REGISTERATION);
        if (registerationFragment == null) {
            registerationFragment = new RegisterationFragment();
        }
        addFragment(registerationFragment,false,REGISTERATION,R.id.frame_layout_auth);
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
        addFragment(eventInnerFragment,false,EVENT_DETAILS,R.id.frame_layout_inner);
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
        Uri uri=Uri.parse("google.navigation:q="+lat+","+lng+"&mode=d");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        context.startActivity(intent);
    }
}
