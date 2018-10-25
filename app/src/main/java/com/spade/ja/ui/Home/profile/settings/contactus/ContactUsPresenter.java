package com.spade.ja.ui.Home.profile.settings.contactus;

import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.contactus.ContactUs;

import javax.inject.Inject;

/**
 * Created by ehab on 3/12/18.
 */

public class ContactUsPresenter extends BasePresenter<ContactUsContract.View> implements ContactUsContract.Presenter {

    private ContactUs contactUs;

    @Inject
    public ContactUsPresenter(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    private BaseCallback<ContactUsResponse> contactUsResponseBaseCallback = new BaseCallback<ContactUsResponse>() {
        @Override
        public void onSuccess(ContactUsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().hideLoading();
                    getView().showSuccess(model.getData().getMsg());
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };

    @Override
    public void unSubscribe() {
        contactUs.unSubscribe();
    }

    @Override
    public void contactUs(String subject, String message) {
        getView().showLoading();
        contactUs.contactUs(subject,message,contactUsResponseBaseCallback);
    }
}
