package com.example.ehab.japroject.ui.Home.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/15/17.
 */

public class EventsFragment extends BaseFragment implements EventsContract.View {


    @Inject
    EventsPresenter presenter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.eventsViewPager)
    ViewPager eventsViewPager;
    private EventsViewPagerAdapter eventsViewPagerAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventsViewPagerAdapter = new EventsViewPagerAdapter(getActivity().getSupportFragmentManager());
        eventsViewPager.setOffscreenPageLimit(4);
        eventsViewPager.setAdapter(eventsViewPagerAdapter);
        tabLayout.setupWithViewPager(eventsViewPager);
    }

    @Override
    public void showError() {

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
        return R.layout.fragment_events;
    }

    @Override
    public void locationIsEnabled() {
        super.locationIsEnabled();
        eventsViewPagerAdapter.getCurrentFragment().locationIsEnabled();
    }
}
