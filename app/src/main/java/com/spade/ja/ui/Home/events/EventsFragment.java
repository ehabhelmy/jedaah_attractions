package com.spade.ja.ui.Home.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.SearchCriteria;
import com.spade.ja.datalayer.pojo.response.category.Category;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.Home.map.adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.filter_list)
    RecyclerView filterList;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.filter_container)
    LinearLayout filterContainer;
    @BindView(R.id.events_list_container)
    LinearLayout eventsListContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private EventsViewPagerAdapter eventsViewPagerAdapter;
    private FilterCategoriesChoosenAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventsViewPagerAdapter = new EventsViewPagerAdapter(getActivity().getSupportFragmentManager());
        eventsViewPager.setAdapter(eventsViewPagerAdapter);
        tabLayout.setupWithViewPager(eventsViewPager);
        //setupCustomTabs();
    }

    @OnClick(R.id.fab)
    void openFilterEvents() {
        presenter.openFilterEvents();
    }

    @Override
    public void showError(String message) {
        if (message != null) {
            showPopUp(message);
        } else {
            showPopUp("Server Error");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void showFilterResults(SearchCriteria searchCriteria) {
        filterContainer.setVisibility(View.VISIBLE);
        eventsListContainer.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        List<Integer> ids = new ArrayList<>();
        for (Cats cats : searchCriteria.getCategoriesNames()) {
            ids.add(cats.getId());
        }
        setupCategoriesChoosen(searchCriteria);
        presenter.filterEvents(searchCriteria.isWeeklySuggested(), ids, searchCriteria.getMonthNumber(), searchCriteria.getLatitiude(), searchCriteria.getLongitude());
    }

    private void setupCategoriesChoosen(SearchCriteria searchCriteria) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        filterList.setLayoutManager(layoutManager);
        filterList.setItemAnimator(new DefaultItemAnimator());
        adapter = new FilterCategoriesChoosenAdapter(getActivity());
        adapter.setCats((ArrayList<Cats>) searchCriteria.getCategoriesNames());
        adapter.setOnCatUnSelected(cats -> {
            List<Integer> ids = new ArrayList<>();
            for (Cats cats1 : adapter.getCats()) {
                ids.add(cats1.getId());
            }
            if (adapter.getCats().isEmpty()) {
                filterContainer.setVisibility(View.GONE);
                eventsListContainer.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
            } else {
                presenter.filterEvents(searchCriteria.isWeeklySuggested(), ids, searchCriteria.getMonthNumber(), searchCriteria.getLatitiude(), searchCriteria.getLongitude());
            }
        });
        filterList.setAdapter(adapter);
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

    @Override
    public void showResults(List<Data> events) {
        list.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_vertical));
        list.setLayoutManager(layoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(dividerItemDecoration);
        DataAdapter dataAdapter = new DataAdapter();
        dataAdapter.setData((ArrayList<Data>) events);
        dataAdapter.setOnItemClick(new DataAdapter.onItemClick() {
            @Override
            public void onLikeClicked(int id, String type) {
                presenter.like(id);
            }

            @Override
            public void onCardClicked(int id, String type) {
                presenter.showEventInner(id);
            }
        });
        list.setAdapter(dataAdapter);
    }

}
