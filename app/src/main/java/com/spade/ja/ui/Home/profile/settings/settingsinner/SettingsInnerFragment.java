package com.spade.ja.ui.Home.profile.settings.settingsinner;

import android.content.ComponentName;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.splash.SplashActivity;
import com.spade.ja.util.LocaleManager;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/11/18.
 */

public class SettingsInnerFragment extends BaseFragment implements SettingsInnerContract.View {

    @Inject
    SettingsInnerPresenter presenter;

    @BindView(R.id.languageChoice)
    TextView languageChoice;

    @BindView(R.id.feedback)
    RelativeLayout feedback;

    @BindView(R.id.about)
    RelativeLayout about;

    @BindView(R.id.contactUs)
    RelativeLayout contactUs;

    @OnClick(R.id.feedback)
    void openEmail() {
        presenter.openFeedback();
    }

    @OnClick(R.id.about)
    void openAboutScreen(){
        presenter.openAboutScreen();
    }

    @OnClick(R.id.contactUs)
    void openContactUs(){
        presenter.openContactUs();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.logout)
    void logout() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle(getString(R.string.logout))
                .setMessage(getString(R.string.logout_confirm))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> presenter.logOut())
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
    }

    @OnClick(R.id.languageChoice)
    void changeLanguage(){
        new AlertDialog.Builder(this.getActivity())
                .setTitle(getString(R.string.change_lang))
                .setMessage(getString(R.string.change_lang_msg))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                    if (getCurrentLanguage().equals(LocaleManager.LANGUAGE_ENGLISH)) {
                        setNewLocale(LocaleManager.LANGUAGE_ARABIC);
                    }else {
                        setNewLocale(LocaleManager.LANGUAGE_ENGLISH);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void setNewLocale(String language) {
        LocaleManager.setNewLocale(getActivity(), language);
        presenter.restartApp();
    }

    private String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }
}
