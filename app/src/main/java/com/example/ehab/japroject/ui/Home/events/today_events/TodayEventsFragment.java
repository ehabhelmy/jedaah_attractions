package com.example.ehab.japroject.ui.Home.events.today_events;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsListAdapter;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/15/17.
 */

public class TodayEventsFragment extends BaseFragment implements TodayEventsContract.View {

    @Inject
    TodayEventsPresenter todayEventsPresenter;

    @BindView(R.id.eventsList)
    RecyclerView recyclerView;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = todayEventsPresenter;
        todayEventsPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_events_list;
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
    public void setupTodayEvents(List<Event> events) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EventsListAdapter eventsListAdapter = new EventsListAdapter(true);
        eventsListAdapter.setData((ArrayList<Event>) events);
        eventsListAdapter.setOnFavouriteListener(model -> {
            Event event = (Event) model;
            //TODO : call presenter to send id of the event to the backend
        });
        recyclerView.setAdapter(eventsListAdapter);
    }
}
