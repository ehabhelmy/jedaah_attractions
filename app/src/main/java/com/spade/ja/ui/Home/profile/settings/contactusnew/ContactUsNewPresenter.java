package com.spade.ja.ui.Home.profile.settings.contactusnew;

import android.content.Intent;
import android.net.Uri;

import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.contactusdata.ContactUsDataUseCase;

import javax.inject.Inject;

public class ContactUsNewPresenter extends BasePresenter<ContactUsNewContract.View> implements ContactUsNewContract.Presenter {

    ContactUsDataUseCase contactUsDataUseCase;

    @Inject
    public ContactUsNewPresenter(ContactUsDataUseCase contactUsDataUseCase) {
        this.contactUsDataUseCase = contactUsDataUseCase;
    }

    @Override
    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        jaNavigationManager.getCurrentActivity().startActivity(intent);
    }

    @Override
    public void sendMail() {
        jaNavigationManager.openEmail();
    }

    @Override
    public void getContactData() {
        getView().showLoading();
        contactUsDataUseCase.contactUs(new BaseCallback<ContactUsDataResponse>() {
            @Override
            public void onSuccess(ContactUsDataResponse model) {
                if (isViewAlive.get()){
                    getView().hideLoading();
                    getView().setupData(model);
                }
            }

            @Override
            public void onError(String message) {
                if (isViewAlive.get()){
                    getView().hideLoading();
                    getView().showError(message);
                }
            }
        });
    }

    @Override
    public void unSubscribe() {
        contactUsDataUseCase.unSubscribe();
    }
}
