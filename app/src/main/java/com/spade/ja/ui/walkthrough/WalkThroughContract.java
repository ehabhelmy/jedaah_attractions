package com.spade.ja.ui.walkthrough;

import com.spade.ja.ui.Base.listener.BaseView;

public interface WalkThroughContract {

    interface View extends BaseView {

    }

    interface Presenter {
        void skip();
    }
}
