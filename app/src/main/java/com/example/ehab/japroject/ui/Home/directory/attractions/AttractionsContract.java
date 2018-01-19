package com.example.ehab.japroject.ui.Home.directory.attractions;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;
import com.example.ehab.japroject.usecase.Unsubscribable;

/**
 * Created by Roma on 1/14/2018.
 */

public interface AttractionsContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter extends Unsubscribable {

    }
}
