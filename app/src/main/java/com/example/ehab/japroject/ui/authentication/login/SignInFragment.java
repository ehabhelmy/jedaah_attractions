package com.example.ehab.japroject.ui.authentication.login;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.authentication.AuthenticationContract;

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

    @OnClick(R.id.skip)
    void skipLogin(){
        activity.skipAuth();
    }

    @OnClick(R.id.forgetPassword)
    void forgetPassword(){

    }

    @OnClick(R.id.join)
    void goToRegisterScreen(){
        presenter.showRegisterScreen();
    }

    @OnClick(R.id.signin)
    void login(){
        //TODO : call presenter and send the email and password
        presenter.login(email.getText().toString().trim(),password.getText().toString().trim());
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
