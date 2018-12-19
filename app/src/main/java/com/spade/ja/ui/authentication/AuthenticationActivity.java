package com.spade.ja.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

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
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    try {
                        String email = object.getString("email");
                        presenter.socialLogin(loginResult.getAccessToken().getUserId(),null,email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
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
            presenter.socialLogin(null,account.getId(),account.getEmail());
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
    public void skipAuth() {
        presenter.goToHomeActivity();
    }

}
