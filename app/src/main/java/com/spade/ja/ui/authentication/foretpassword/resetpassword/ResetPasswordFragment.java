package com.spade.ja.ui.authentication.foretpassword.resetpassword;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.authentication.AuthenticationContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPasswordFragment extends BaseFragment implements ResetPasswordContract.View {

    @Inject
    ResetPasswordPresenter resetPasswordPresenter;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @BindView(R.id.container)
    LinearLayout container;

    @BindView(R.id.code)
    AppCompatEditText code;

    @BindView(R.id.password)
    AppCompatEditText password;

    @BindView(R.id.confirmPassword)
    AppCompatEditText confirmPassword;

    private AuthenticationContract.View activity;


    @OnClick(R.id.reset)
    void resetPassword(){
        if (TextUtils.isEmpty(password.getText().toString().trim()) || TextUtils.isEmpty(confirmPassword.getText().toString().trim())){
            showPopUp("You must enter both password and confirmation password");
        }else if (password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
            resetPasswordPresenter.resetPassword(password.getText().toString().trim(),code.getText().toString().trim());
        }else {
            showPopUp("Password and confirmation password doesn't match");
        }
    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = resetPasswordPresenter;
        resetPasswordPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void showSuccess(String msg) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.succes))
                .setMessage(msg)
                .setPositiveButton(getString(R.string.login), (dialogInterface, i) -> {
                    resetPasswordPresenter.goToLogin();
                }).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }


    @OnClick(R.id.skip)
    void skipLogin(){
        activity.skipAuth();
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }
}
