package com.spade.ja.ui.authentication.registeration;

import android.net.Uri;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by Romisaa on 12/20/2017.
 */

public interface RegisterationContract {

    interface View extends BaseView, ErrorView, ProgressView {

    }

    interface Presenter extends Unsubscribable{
        void register(String userName, String email, String password, String mobile, Uri image);
        void showSignIn();
    }
}
