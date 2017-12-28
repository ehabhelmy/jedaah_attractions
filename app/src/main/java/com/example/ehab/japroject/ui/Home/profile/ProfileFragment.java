package com.example.ehab.japroject.ui.Home.profile;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
import com.example.ehab.japroject.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    @Inject
    ProfilePresenter presenter;

    @BindView(R.id.profile_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.profile_viewpager)
    ViewPager profileViewPager;

    @BindView(R.id.edit)
    TextView editTextView;

    @BindView(R.id.username)
    TextView userName;

    @BindView(R.id.id)
    TextView id;

    private ProfileViewPagerAdapter profileViewPagerAdapter;

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
        return R.layout.fragment_profile;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        profileViewPagerAdapter = new ProfileViewPagerAdapter(getActivity().getSupportFragmentManager());
        profileViewPager.setOffscreenPageLimit(4);
        profileViewPager.setAdapter(profileViewPagerAdapter);
        tabLayout.setupWithViewPager(profileViewPager);
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

    @Override
    public void updateProfileFragment(ProfileResponse model) {
        userName.setText(model.getData().getName());
        id.setText(model.getData().getId());
    }
}
