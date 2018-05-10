package com.spade.ja.ui.authentication.foretpassword.code;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.authentication.AuthenticationContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class GetCodeFragment extends BaseFragment implements GetCodeContract.View{

    @Inject
    GetCodePresenter getCodePresenter;

    @BindView(R.id.email)
    AppCompatEditText email;

    private AuthenticationContract.View activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }

    @OnClick(R.id.skip)
    void skipLogin(){
        activity.skipAuth();
    }

    @OnClick(R.id.reset)
    void getResetCode(){
        if (TextUtils.isEmpty(email.getText().toString().trim())){
            showPopUp("You have to enter email");
        }else {
            getCodePresenter.getCode(email.getText().toString().trim());
        }
    }
    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = getCodePresenter;
        getCodePresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_code;
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
}
