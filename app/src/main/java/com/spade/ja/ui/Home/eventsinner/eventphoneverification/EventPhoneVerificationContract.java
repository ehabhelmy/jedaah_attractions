package com.spade.ja.ui.Home.eventsinner.eventphoneverification;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.eventsinner.smsreciever.SMSReciever;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 1/25/18.
 */

public interface EventPhoneVerificationContract {

    interface View extends BaseView,ErrorView,ProgressView, android.view.View.OnFocusChangeListener, android.view.View.OnKeyListener,SMSReciever.SMSListener {

    }

    interface Presenter extends Unsubscribable {
        void showOrderView();
        void sendMessage();
    }
}
