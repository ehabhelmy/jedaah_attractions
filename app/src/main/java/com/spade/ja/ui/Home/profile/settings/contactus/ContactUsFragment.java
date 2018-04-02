package com.spade.ja.ui.Home.profile.settings.contactus;

import android.support.v7.widget.AppCompatEditText;

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

    @BindView(R.id.subject)
    AppCompatEditText subject;

    @BindView(R.id.messageus)
    AppCompatEditText message;

    @OnClick(R.id.send)
    void sendMessage() {
        presenter.contactUs(subject.getText().toString().trim(),message.getText().toString().trim());
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
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
        return R.layout.fragment_contactus;
    }

    @Override
    public void showSuccess(String msg) {
        showPopUp(msg);
    }
}
