package com.example.ehab.japroject.di;

import com.example.ehab.japroject.ui.Home.HomeActivity;
import com.example.ehab.japroject.ui.Home.events.EventsFragment;
import com.example.ehab.japroject.ui.Home.events.today_events.TodayEventsFragment;
import com.example.ehab.japroject.ui.Home.events.week_events.WeekEventsFragment;
import com.example.ehab.japroject.ui.Home.events.all_events.AllEventsFragment;
import com.example.ehab.japroject.ui.Home.events.nearby_events.NearByEventsFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.EventInnerFragment;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;
import com.example.ehab.japroject.ui.authentication.AuthenticationActivity;
import com.example.ehab.japroject.ui.authentication.login.SignInFragment;
import com.example.ehab.japroject.ui.authentication.socialmedia.SocialMediaFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ehab on 12/1/17.
 */

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    //TODO : inject activities here

    void inject(HomeActivity homeActivity);

    void inject(ExploreFragment exploreFragment);

    void inject(EventsFragment eventsFragment);

    void inject(TodayEventsFragment todayEventsFragment);

    void inject(WeekEventsFragment weekEventsFragment);

    void inject(AllEventsFragment allEventsFragment);

    void inject(NearByEventsFragment nearByEventsFragment);

    void inject(SocialMediaFragment socialMediaFragment);

    void inject(AuthenticationActivity authenticationActivity);

    void inject(EventInnerFragment eventInnerFragment);

    void inject(SignInFragment signInFragment);
}

