package com.spade.ja.ui.authentication.socialmedia;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 12/19/17.
 */

public interface SocialMediaContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter {

        void showSignInScreen();
    }
}
