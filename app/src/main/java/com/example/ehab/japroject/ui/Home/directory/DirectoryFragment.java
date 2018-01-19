package com.example.ehab.japroject.ui.Home.directory;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.events.EventsPresenter;
import com.example.ehab.japroject.ui.Home.events.EventsViewPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Roma on 1/14/2018.
 */

public class DirectoryFragment extends BaseFragment implements DirectoryContract.View {

    @Inject
    DirectoryPresenter presenter;
    @BindView(R.id.directoryTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.directoryViewPager)
    ViewPager directoryViewPager;
    private DirectoryViewPagerAdapter directoryViewPagerAdapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        directoryViewPagerAdapter = new DirectoryViewPagerAdapter(getActivity().getSupportFragmentManager());
        directoryViewPager.setAdapter(directoryViewPagerAdapter);
        tabLayout.setupWithViewPager(directoryViewPager);
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
       return R.layout.fragment_directory;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
