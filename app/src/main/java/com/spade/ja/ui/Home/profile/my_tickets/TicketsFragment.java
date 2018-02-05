package com.spade.ja.ui.Home.profile.my_tickets;


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
import com.spade.ja.ui.Home.profile.my_tickets.adapter.HistoryListAdapter;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class TicketsFragment extends BaseFragment implements TicketsContract.View {

    @Inject
    TicketsPresenter presenter;

    @BindView(R.id.upcomingList)
    RecyclerView upcomingList;

    @BindView(R.id.pastList)
    RecyclerView pastList;

    @BindView(R.id.noEvents)
    TextView noEvents;

    @BindView(R.id.textView30)
    TextView upcomingEvents;

    @BindView(R.id.textView31)
    TextView pastEvents;


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
        return R.layout.fragment_tickets;
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
    public void setupUpcomingList(List<HistoryEventsUi> data) {
        if (data.size() == 0) {
            upcomingEvents.setVisibility(View.GONE);
            upcomingList.setVisibility(View.GONE);
            noEvents.setVisibility(View.VISIBLE);
        }else {
            noEvents.setVisibility(View.GONE);
            setupRecyclerView(upcomingList, data, HistoryListAdapter.HistoryType.UPCOMING.name());
        }
    }

    @Override
    public void setupPastList(List<HistoryEventsUi> data) {
        if (data.size() == 0) {
            pastEvents.setVisibility(View.GONE);
            pastList.setVisibility(View.GONE);
            noEvents.setVisibility(View.VISIBLE);
        }else {
            noEvents.setVisibility(View.GONE);
            setupRecyclerView(pastList, data, HistoryListAdapter.HistoryType.PAST.name());
        }
    }
    private void setupRecyclerView(RecyclerView recyclerView,List<HistoryEventsUi> data,String type) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(dividerItemDecoration);
        HistoryListAdapter historyListAdapter = new HistoryListAdapter();
        historyListAdapter.setData((ArrayList<HistoryEventsUi>) data);
        historyListAdapter.setType(type);
        recyclerView.setAdapter(historyListAdapter);
    }
}
