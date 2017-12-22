package com.example.ehab.japroject.ui.authentication.login;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by ehab on 12/21/17.
 */

public interface SignInContract  {

    interface View extends BaseView,ErrorView,ProgressView{

    }

    interface Presenter extends Unsubscribable {
        void login(String email,String password);
        void getResetPasswordCode();
        void resetPassword(String newPassword);
    }
}
