package com.spade.ja.ui.Home.profile.settings.about;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.usecase.Unsubscribable;

public interface AboutUsContract {

    interface View extends BaseView,ErrorView {
        void setupAboutText(String about);
    }

    interface Presenter extends Unsubscribable {

    }
}
