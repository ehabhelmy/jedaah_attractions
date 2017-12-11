package com.example.ehab.japroject.ui.Home.explore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.TopEventsListAdapter;

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

    private RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);

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
        topEvents.setLayoutManager(layoutManager);
        TopEventsListAdapter topEventsListAdapter = new TopEventsListAdapter();
    }
}
