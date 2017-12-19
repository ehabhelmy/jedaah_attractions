package com.example.ehab.japroject.ui.authentication;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.facebook.CallbackManager;

/**
 * Created by ehab on 12/19/17.
 */

public interface AuthenticationContract {

    interface View extends BaseView {
        void loginWithFacebook();
        void loginWithGoogle();
    }

    interface Presenter {

    }
}
