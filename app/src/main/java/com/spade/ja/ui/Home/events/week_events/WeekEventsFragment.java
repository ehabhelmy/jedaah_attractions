package com.spade.ja.ui.Home.events.week_events;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.explore.adapter.EventsListAdapter;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/15/17.
 */

public class WeekEventsFragment extends BaseFragment implements WeekEventsContract.View {

    @Inject
    WeekEventsPresenter weekEventsPresenter;

    @BindView(R.id.eventsList)
    RecyclerView recyclerView;

    @BindView(R.id.noEvents)
    RelativeLayout noEvents;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = weekEventsPresenter;
        weekEventsPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_events_list;
    }

    @Override
    protected String getScreenTrackingName() {
        return "week events list";
    }

    @Override
    public void showError(String message) {
        if (message.equals(Constants.NO_TOKEN)) {
            showLoginRequiredError();
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

    @Override
    public void setupWeekEvents(List<Event> events) {
        if (events.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noEvents.setVisibility(View.GONE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(dividerItemDecoration);
            EventsListAdapter eventsListAdapter = new EventsListAdapter(true);
            eventsListAdapter.setData((ArrayList<Event>) events);
            eventsListAdapter.setOnFavouriteListener(id -> {
                //TODO : call presenter to send id of the event to the backend
                weekEventsPresenter.like(id);
            });
            eventsListAdapter.setOnViewListener(id -> {
                weekEventsPresenter.showEventInner(id);
            });
            recyclerView.setAdapter(eventsListAdapter);
        }else {
            recyclerView.setVisibility(View.GONE);
            noEvents.setVisibility(View.VISIBLE);
        }
    }
}
