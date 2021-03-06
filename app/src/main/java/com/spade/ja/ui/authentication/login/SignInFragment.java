package com.spade.ja.ui.authentication.login;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.authentication.AuthenticationContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/21/17.
 */

public class SignInFragment extends BaseFragment implements SignInContract.View {

    @Inject
    SignInPresenter presenter;

    private AuthenticationContract.View activity;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @BindView(R.id.loginContainer)
    RelativeLayout loginContainer;

    @BindView(R.id.forgetPassword)
    TextView forgetPassword;

    @BindView(R.id.email)
    AppCompatEditText email;

    @BindView(R.id.password)
    AppCompatEditText password;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgetPassword.setPaintFlags(forgetPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }

    @Override
    public void showError(String message) {
        if (message !=  null) {
            showPopUp(message);
        }else {
            showPopUp("Server Error");
        }
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        loginContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        loginContainer.setVisibility(View.VISIBLE);
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
        return R.layout.fragment_login;
    }

    @Override
    protected String getScreenTrackingName() {
        return "login";
    }

    @OnClick(R.id.skip)
    void skipLogin(){
        activity.skipAuth();
    }

    @OnClick(R.id.forgetPassword)
    void resetPassword(){
        presenter.getResetPasswordCode();
    }

    @OnClick(R.id.join)
    void goToRegisterScreen(){
        hideKeyboard();
        presenter.showRegisterScreen();
    }

    @OnClick(R.id.signin)
    void login(){
        hideKeyboard();
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
            showPopUp(getString(R.string.enter_mail));
        }else if (TextUtils.isEmpty(password.getText())){
            showPopUp(getString(R.string.enter_pass));
        }else {
            presenter.login(email.getText().toString().trim(), password.getText().toString().trim());
        }
    }

    @Override
    public void onErrorAuth(String message) {
        super.onErrorAuth(message);
        if (message !=  null) {
            showPopUp(message);
        }else {
            showPopUp("Server Error");
        }
    }
}
