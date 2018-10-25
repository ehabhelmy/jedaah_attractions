package com.spade.ja.ui.Home.profile.settings.contactus;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/12/18.
 */

public class ContactUsFragment extends BaseFragment implements ContactUsContract.View {

    @Inject
    ContactUsPresenter presenter;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @BindView(R.id.loginContainer)
    RelativeLayout contactUsContainer;

    @BindView(R.id.subject)
    AppCompatEditText subject;

    @BindView(R.id.messageus)
    AppCompatEditText message;

    @OnClick(R.id.send)
    void sendMessage() {
        if (TextUtils.isEmpty(subject.getText().toString().trim()) || TextUtils.isEmpty(message.getText().toString().trim())) {
            showPopUp("You have to enter both subject and message");
        }else{
            presenter.contactUs(subject.getText().toString().trim(), message.getText().toString().trim());
        }
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        contactUsContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        contactUsContainer.setVisibility(View.VISIBLE);
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
        return R.layout.fragment_contactus;
    }

    @Override
    public void showSuccess(String msg) {
        new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setTitle(getString(R.string.contactUs))
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }
}
