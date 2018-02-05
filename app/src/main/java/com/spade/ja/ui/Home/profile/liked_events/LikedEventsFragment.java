package com.spade.ja.ui.Home.profile.liked_events;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.explore.adapter.EventsListAdapter;
import com.spade.ja.ui.Home.explore.pojo.Event;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class LikedEventsFragment extends BaseFragment implements LikedEventsContract.View {

    @Inject
    LikedEventsPresenter presenter;

    @BindView(R.id.eventsList)
    RecyclerView eventsList;
    @BindView(R.id.noEvents)
    TextView noEvents;

    private EventsListAdapter eventsListAdapter;

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
        return R.layout.fragment_liked_events;
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setupAllEvents(List<Event> events) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        eventsList.setLayoutManager(layoutManager);
        eventsList.addItemDecoration(dividerItemDecoration);
        eventsList.setItemAnimator(new DefaultItemAnimator());
        eventsListAdapter = new EventsListAdapter(true);
        eventsListAdapter.setData((ArrayList<Event>) events);
        eventsListAdapter.setOnFavouriteListener(id -> {
            //TODO : call presenter to send id of the event to the backend
            presenter.like(id);
        });
        eventsListAdapter.setOnViewListener(id -> {
            presenter.showEventInner(id);
        });
        eventsList.setAdapter(eventsListAdapter);
    }

    @Override
    public void showNoEvents() {
        eventsList.setVisibility(View.GONE);
        noEvents.setVisibility(View.VISIBLE);
    }
}
