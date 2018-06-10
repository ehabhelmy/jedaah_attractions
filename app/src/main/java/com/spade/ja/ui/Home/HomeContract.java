package com.spade.ja.ui.Home;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 12/2/17.
 */

public interface HomeContract {
    interface View extends BaseView,ProgressView,ErrorView{
        boolean isLocationPermissionGranted();
        boolean isLocationServicesEnabled();
        void openEventsList();
        void showEventsListing();
        void showDirectoryListing();
        void showSearch();
    }
    interface Presenter{
        void showExplore();
        void showSearch();
        void showEvents();
        void showProfile();
        void showDirectory();
        void locationIsEnabled();
        void hideNearByEvents();
        <F extends BaseFragment> F getCurrentFragmentOnHome();
    }
}
