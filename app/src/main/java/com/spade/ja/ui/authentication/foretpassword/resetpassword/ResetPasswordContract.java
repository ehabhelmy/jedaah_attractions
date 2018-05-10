package com.spade.ja.ui.authentication.foretpassword.resetpassword;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

public interface ResetPasswordContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void showSuccess(String msg);
    }

    interface Presenter extends Unsubscribable {
        void resetPassword(String password);
        void goToLogin();
    }
}
