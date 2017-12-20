package com.example.ehab.japroject.ui.Home.profile;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by Romisaa on 12/17/2017.
 */

public interface ProfileContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter extends Unsubscribable {
    }
}
