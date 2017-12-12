package com.example.ehab.japroject.ui.Home.explore;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.TopEventsListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/11/17.
 */

public class ExploreFragment extends BaseFragment implements ExploreContract.View {

    @Inject
    ExplorePresenter presenter;

    @BindView(R.id.topEvents)
    RecyclerView topEvents;

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
        return R.layout.fragment_explore;
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
    public void setupTopEvents(List<Datum> events) {
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.divider));
        topEvents.setLayoutManager(layoutManager);
        topEvents.addItemDecoration(dividerItemDecoration);
        topEvents.setItemAnimator(new DefaultItemAnimator());
        TopEventsListAdapter topEventsListAdapter = new TopEventsListAdapter();
        topEventsListAdapter.setData((ArrayList<Datum>) events);
        topEventsListAdapter.setOnFavouriteListener(model -> {
            Datum datum = (Datum) model;
            //TODO : call presenter to send id of the event to the backend
        });
        topEvents.setAdapter(topEventsListAdapter);
    }
}
