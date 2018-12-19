package com.spade.ja.ui.Home.profile.settings.settingsinner;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 3/11/18.
 */

public interface SettingsInnerContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter {
        void openFeedback();
        void openAboutScreen();
        void openContactUs();
        void logOut();
        void restartApp();
    }
}
