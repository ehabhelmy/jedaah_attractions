package com.example.ehab.japroject.ui.Home;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.Base.listener.ProgressView;

/**
 * Created by ehab on 12/2/17.
 */

public interface HomeContract {
    interface View extends BaseView,ProgressView,ErrorView{

    }
    interface Presenter{

    }
}
