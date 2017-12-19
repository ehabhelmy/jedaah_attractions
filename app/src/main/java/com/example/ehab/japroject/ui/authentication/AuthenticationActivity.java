package com.example.ehab.japroject.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by ehab on 12/19/17.
 */

public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View{

    @Inject
    AuthenticationPresenter presenter;

    @Inject
    LoginManager loginManager;

    @Inject
    CallbackManager callbackManager;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // TODO : call api and send user id to it
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loginWithFacebook() {
        loginManager.logInWithPublishPermissions(this, Arrays.asList("email", "public_profile", "user_birthday"));
    }

    @Override
    public void loginWithGoogle() {

    }
}
