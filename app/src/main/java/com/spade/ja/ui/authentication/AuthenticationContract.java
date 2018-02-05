package com.spade.ja.ui.authentication;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 12/19/17.
 */

public interface AuthenticationContract {

    interface View extends BaseView {
        void loginWithFacebook();
        void loginWithGoogle();
        void skipAuth();
    }

    interface Presenter extends Unsubscribable{
        void socialLogin(String facebookId,String googleId,String email);
        void goToHomeActivity();
    }
}
