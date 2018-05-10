package com.spade.ja.ui.authentication.foretpassword.code;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

public interface GetCodeContract {

    interface View extends BaseView,ErrorView,ProgressView {
    }

    interface Presenter extends Unsubscribable {
        void getCode(String email);
    }
}
