package com.example.ehab.japroject.ui.Home.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
        eventsViewPager.setAdapter(eventsViewPagerAdapter);
        tabLayout.setupWithViewPager(eventsViewPager);
        setupCustomTabs();
    }

    private void setupCustomTabs() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv=(TextView) LayoutInflater.from(this.getContext()).inflate(R.layout.custom_tab_item,null);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
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
