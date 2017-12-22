package com.example.ehab.japroject.ui.authentication.registeration;


import android.support.v7.widget.AppCompatImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Romisaa on 12/20/2017.
 */

public class RegisterationFragment extends BaseFragment implements RegisterationContract.View {

    @BindView(R.id.username)
    TextView userName;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.password)
    TextView password;

    @BindView(R.id.register)
    Button register;

    @BindView(R.id.add_image)
    AppCompatImageButton addImage;

    @BindView(R.id.profile_image)
    ImageView profileImage;

    @Inject
    RegisterationPresenter presenter;


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
        return R.layout.fragment_register;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
