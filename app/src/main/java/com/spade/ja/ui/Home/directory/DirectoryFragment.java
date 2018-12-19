package com.spade.ja.ui.Home.directory;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.SearchCriteria;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.HomeActivity;
import com.spade.ja.ui.Home.directory.attractions.AttractionFragment;
import com.spade.ja.ui.Home.directory.venues.VenuesFragment;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.Home.map.adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

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
        if (getArguments() != null){
            if (getArguments().getInt(HomeActivity.TYPE) == 3) {
                directoryViewPager.setCurrentItem(1);
            }
        }
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

    public void showFilterResultsVenue(SearchCriteria searchCriteria) {
        BaseFragment fragment = (BaseFragment) directoryViewPagerAdapter.getItem(directoryViewPager.getCurrentItem());
        if (fragment instanceof VenuesFragment) {
            VenuesFragment venuesFragment = (VenuesFragment) directoryViewPager.getAdapter().instantiateItem(directoryViewPager, directoryViewPager.getCurrentItem());
            venuesFragment.showFilterResults(searchCriteria);
        }
    }

    public void showFilterResultsAttraction(SearchCriteria searchCriteria) {
        BaseFragment fragment = (BaseFragment) directoryViewPagerAdapter.getItem(directoryViewPager.getCurrentItem());
        if (fragment instanceof AttractionFragment) {
            AttractionFragment attractionFragment = (AttractionFragment) directoryViewPager.getAdapter().instantiateItem(directoryViewPager, directoryViewPager.getCurrentItem());
            attractionFragment.showFilterResults(searchCriteria);
        }
    }
}
