package com.spade.ja.ui.Home.profile;

import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by Romisaa on 12/17/2017.
 */

public interface ProfileContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void updateProfileFragment(Data model);
    }

    interface Presenter extends Unsubscribable {
        void logOut();
        void editProfile();
    }
}
