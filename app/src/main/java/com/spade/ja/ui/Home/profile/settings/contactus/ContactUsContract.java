package com.spade.ja.ui.Home.profile.settings.contactus;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 3/12/18.
 */

public interface ContactUsContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void showSuccess(String msg);
    }

    interface Presenter extends Unsubscribable {
        void contactUs(String subject,String message);
    }
}
