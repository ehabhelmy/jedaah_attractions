package com.example.ehab.japroject.ui.authentication.socialmedia;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.authentication.AuthenticationContract;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/18/17.
 */

public class SocialMediaFragment extends BaseFragment implements SocialMediaContract.View{

    @BindView(R.id.skip)
    TextView skip;

    @BindView(R.id.facebook)
    Button facebook;

    @BindView(R.id.google)
    Button google;

    @BindView(R.id.signin)
    Button signIn;

    @BindView(R.id.register)
    Button register;

    @BindView(R.id.login_button)
    LoginButton loginButton;

    @Inject
    SocialMediaPresenter presenter;

    private AuthenticationContract.View activity;

    @OnClick(R.id.facebook)
    void loginWithFacebook(){
        activity.loginWithFacebook();
    }

    @OnClick(R.id.google)
    void loginWithGoogle(){
        activity.loginWithGoogle();
    }

    @OnClick(R.id.signin)
    void login(){

    }

    @OnClick(R.id.register)
    void register(){

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
        return R.layout.fragment_authentication;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
