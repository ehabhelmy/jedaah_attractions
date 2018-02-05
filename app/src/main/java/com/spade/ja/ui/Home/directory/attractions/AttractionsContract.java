package com.spade.ja.ui.Home.directory.attractions;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by Roma on 1/14/2018.
 */

public interface AttractionsContract {

    interface View extends BaseView,ErrorView,ProgressView {

    }

    interface Presenter extends Unsubscribable {

    }
}
