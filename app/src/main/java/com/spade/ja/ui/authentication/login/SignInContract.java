package com.spade.ja.ui.authentication.login;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

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
        void showRegisterScreen();
    }
}
