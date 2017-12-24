package com.example.ehab.japroject.ui.authentication.registeration;

import android.net.Uri;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;

/**
 * Created by Romisaa on 12/20/2017.
 */

public interface RegisterationContract {

    interface View extends BaseView, ErrorView, ProgressView {

    }

    interface Presenter {
        void register(String userName, String email, String password, String mobile, Uri image);
        void showSignIn();
    }
}
