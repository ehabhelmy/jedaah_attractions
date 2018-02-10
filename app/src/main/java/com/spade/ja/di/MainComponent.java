package com.spade.ja.di;

import com.spade.ja.ui.Home.HomeActivity;
import com.spade.ja.ui.Home.directory.DirectoryFragment;
import com.spade.ja.ui.Home.directory.attractions.AttractionFragment;
import com.spade.ja.ui.Home.directory.venues.VenuesFragment;
import com.spade.ja.ui.Home.events.EventsFragment;
import com.spade.ja.ui.Home.events.all_events.AllEventsFragment;
import com.spade.ja.ui.Home.events.nearby_events.NearByEventsFragment;
import com.spade.ja.ui.Home.eventsinner.EventInnerActivity;
import com.spade.ja.ui.Home.eventsinner.eventbuy.EventPaymentFragment;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.spade.ja.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.spade.ja.ui.Home.events.today_events.TodayEventsFragment;
import com.spade.ja.ui.Home.events.week_events.WeekEventsFragment;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;
import com.spade.ja.ui.Home.eventsinner.eventphoneverification.EventPhoneVerificationFragment;
import com.spade.ja.ui.Home.explore.ExploreFragment;
import com.spade.ja.ui.Home.map.MapActivity;
import com.spade.ja.ui.Home.profile.ProfileFragment;
import com.spade.ja.ui.Home.profile.edit.EditActivity;
import com.spade.ja.ui.Home.profile.liked_events.LikedEventsFragment;
import com.spade.ja.ui.Home.profile.my_tickets.TicketsFragment;
import com.spade.ja.ui.Home.venueinner.VenueInnerActivity;
import com.spade.ja.ui.Home.venueinner.venuedetails.VenueDetailsFragment;
import com.spade.ja.ui.authentication.AuthenticationActivity;
import com.spade.ja.ui.authentication.login.SignInFragment;
import com.spade.ja.ui.authentication.registeration.RegisterationFragment;
import com.spade.ja.ui.authentication.socialmedia.SocialMediaFragment;
import com.spade.ja.ui.splash.SplashActivity;

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

    void inject(RegisterationFragment registerationFragment);

    void inject(EventInnerActivity eventInnerActivity);

    void inject(EventPaymentFragment eventPaymentFragment);

    void inject(EventOrderFragment eventOrderFragment);

    void inject(ProfileFragment profileFragment);

    void inject(LikedEventsFragment likedEventsFragment);

    void inject(TicketsFragment ticketsFragment);

    void inject(SplashActivity splashActivity);

    void inject(EventOrderSuccessFragment orderSuccessFragment);

    void inject(DirectoryFragment directoryFragment);

    void inject(VenuesFragment venuesFragment);

    void inject(AttractionFragment attractionFragment);

    void inject(VenueInnerActivity venueInnerActivity);

    void inject(VenueDetailsFragment venueDetailsFragment);

    void inject(EventPhoneVerificationFragment eventPhoneVerificationFragment);

    void inject(EditActivity editActivity);

    void inject(MapActivity mapActivity);

}

