package com.spade.ja.ui.Home.profile.settings.about;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.about.AboutUsResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.about.AboutUs;

import javax.inject.Inject;

public class AboutUsPresenter extends BasePresenter<AboutUsContract.View> implements AboutUsContract.Presenter {

    private AboutUs aboutUs;
    private BaseCallback<AboutUsResponse> aboutUsResponseBaseCallback = new BaseCallback<AboutUsResponse>() {
        @Override
        public void onSuccess(AboutUsResponse model) {
            if (isViewAlive.get()){
                getView().setupAboutText(model.getData().get(0).getParagraph());
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public AboutUsPresenter(AboutUs aboutUs) {
        this.aboutUs = aboutUs;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        aboutUs.about(aboutUsResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        aboutUs.unSubscribe();
    }
}
