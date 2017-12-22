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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

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

    private GoogleSignInClient mGoogleSignInClient;

    public static final int RC_SIGN_IN = 3;

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
                presenter.login(loginResult.getAccessToken().getUserId(),"");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignIn(googleSignInAccountTask);
        }
    }

    private void handleGoogleSignIn(Task<GoogleSignInAccount> googleSignInAccountTask){
        try {
            GoogleSignInAccount account = googleSignInAccountTask.getResult(ApiException.class);
            //account.getIdToken()  ---> call api and send this to the backend to authenticate
            presenter.login(account.getId(),"");
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginWithFacebook() {
        loginManager.logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_birthday"));
    }

    @Override
    public void loginWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void login(String email, String password) {
        presenter.login(email,password);
    }

    @Override
    public void skipAuth() {
        jaNavigationManager.goToHomeActivity();
    }

}
