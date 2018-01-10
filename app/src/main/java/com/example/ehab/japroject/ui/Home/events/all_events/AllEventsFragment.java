package com.example.ehab.japroject.ui.Home.events.all_events;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.events.all_events.adapter.AllEventsListAdapter;
import com.example.ehab.japroject.ui.Home.events.all_events.listener.OnLoadMoreListener;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsListAdapter;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/15/17.
 */

public class AllEventsFragment extends BaseFragment implements AllEventsContract.View {

    @Inject
    AllEventsPresenter presenter;

    @BindView(R.id.eventsList)
    RecyclerView eventsList;

    @BindView(R.id.noEvents)
    RelativeLayout noEvents;

    private List<Event> events;
    private AllEventsListAdapter eventsListAdapter;

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
        return R.layout.fragment_events_list;
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
    public void setupAllEvents(List<Event> events) {
        if (events.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
            eventsList.setLayoutManager(layoutManager);
            eventsList.addItemDecoration(dividerItemDecoration);
            eventsList.setItemAnimator(new DefaultItemAnimator());
            this.events = events;
            eventsListAdapter = new AllEventsListAdapter();
            eventsListAdapter.setData((ArrayList<Event>) events);
            eventsListAdapter.setOnFavouriteListener(id -> {
                //TODO : call presenter to send id of the event to the backend
                presenter.like(id);
            });
            eventsListAdapter.setOnViewListener(id -> {
                presenter.showEventInner(id);
            });
            eventsListAdapter.setupLoadingRecyclerView(eventsList);
            eventsListAdapter.setOnLoadMoreListener(() -> {
                if (events.size() <= 9) {
                    events.add(null);
                    eventsListAdapter.notifyItemInserted(events.size() - 1);
                    presenter.addEvents();
                } else {
                    Toast.makeText(getActivity(), "No More Events", Toast.LENGTH_SHORT).show();
                }
            });
            eventsList.setAdapter(eventsListAdapter);
        }else {
            eventsList.setVisibility(View.GONE);
            noEvents.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addEvents(List<Event> events) {
        this.events.remove(this.events.size() - 1);
        //eventsList.removeViewAt(events.size() - 1);
        eventsListAdapter.notifyItemRemoved(this.events.size());
        eventsListAdapter.setLoading(false);
        if (events != null) {
            eventsListAdapter.addData((ArrayList<Event>) events);
        }
    }
}
