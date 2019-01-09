package com.spade.ja.ui.authentication.socialmedia;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.authentication.AuthenticationContract;
import com.facebook.login.widget.LoginButton;

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
    void loginWithFacebook() {
        activity.loginWithFacebook();
    }

    @OnClick(R.id.google)
    void loginWithGoogle() {
        activity.loginWithGoogle();
    }

    @OnClick(R.id.signin)
    void login(){
        presenter.showSignInScreen();
    }

    @OnClick(R.id.register)
    void register() {
        presenter.showRegisterationScreen();
    }

    @OnClick(R.id.skip)
    void skipLogin(){
        activity.skipAuth();
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
    protected String getScreenTrackingName() {
        return "social media";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }

    @Override
    public void onErrorAuth(String message) {
        super.onErrorAuth(message);
        //TODO : show popup error
    }

    @Override
    public void showError(String message) {
        if (message !=  null) {
            showPopUp(message);
        }else {
            showPopUp("Server Error");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
