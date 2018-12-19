package com.spade.ja.ui.Home.profile.settings.contactusnew;

import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

public interface ContactUsNewContract {
    interface View extends BaseView,ErrorView,ProgressView {
        void setupData(ContactUsDataResponse contactUsResponse);
    }

    interface Presenter extends Unsubscribable {
        void call(String phone);
        void sendMail();
        void getContactData();
    }

}
