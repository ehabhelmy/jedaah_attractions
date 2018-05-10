package com.spade.ja.ui.Home.profile.liked_directory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.directory.venues.adapter.ItemOffsetDecoration;
import com.spade.ja.ui.Home.profile.liked_directory.adapter.LikedDirectoryListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class LikedDirectoryFragment extends BaseFragment implements LikedDirectoryContract.View{


    @Inject
    LikedDirectoryPresenter presenter;

    @BindView(R.id.likedDirectoryList)
    RecyclerView allVenuesRecyclarView;

    @BindView(R.id.noAttractions)
    TextView noData;

    private ArrayList<Venue> response;
    private LikedDirectoryListAdapter allVenuesListAdapter;
    private boolean firstDataFetch = true;

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
        return R.layout.fragment_liked_directory;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2);
        allVenuesRecyclarView.setLayoutManager(mLayoutManager);
        allVenuesRecyclarView.setItemAnimator(new DefaultItemAnimator());
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this.getContext(), R.dimen.item_offset);
        allVenuesRecyclarView.addItemDecoration(itemDecoration);
    }

    @Override
    public void setupAllAttractions(List<Venue> venuesResponses) {
        noData.setVisibility(View.GONE);
        allVenuesRecyclarView.setVisibility(View.VISIBLE);
        if (firstDataFetch) {
            allVenuesListAdapter = new LikedDirectoryListAdapter(true);
            response = (ArrayList<Venue>) venuesResponses;
            allVenuesListAdapter.setData((ArrayList<Venue>) venuesResponses);
            allVenuesListAdapter.setOnDirectoryAction(new LikedDirectoryListAdapter.OnDirectoryAction() {
                @Override
                public void onDirectoryLike(int id, String type) {
                    if (type.equals("attraction")){

                    }else {

                    }
                }

                @Override
                public void onDirectoryClick(int id, String type) {
                    if (type.equals("attraction")){

                    }else {

                    }
                }
            });
            allVenuesRecyclarView.setAdapter(allVenuesListAdapter);
            firstDataFetch = false;
        }else {
            ArrayList<Venue> venues = allVenuesListAdapter.getData();
            venues.addAll(venuesResponses);
            Collections.shuffle(venues);
            allVenuesListAdapter.updateData(venues);
        }
    }

    @Override
    public void setupAllVenues(List<Venue> venuesResponses) {
        noData.setVisibility(View.GONE);
        allVenuesRecyclarView.setVisibility(View.VISIBLE);
        if (firstDataFetch) {
            allVenuesListAdapter = new LikedDirectoryListAdapter(true);
            response = (ArrayList<Venue>) venuesResponses;
            allVenuesListAdapter.setData((ArrayList<Venue>) venuesResponses);
            allVenuesListAdapter.setOnDirectoryAction(new LikedDirectoryListAdapter.OnDirectoryAction() {
                @Override
                public void onDirectoryLike(int id, String type) {
                    if (type.equals("attraction")){

                    }else {

                    }
                }

                @Override
                public void onDirectoryClick(int id, String type) {
                    if (type.equals("attraction")){

                    }else {

                    }
                }
            });
            allVenuesRecyclarView.setAdapter(allVenuesListAdapter);
            firstDataFetch = false;
        }else {
            ArrayList<Venue> venues = allVenuesListAdapter.getData();
            venues.addAll(venuesResponses);
            Collections.shuffle(venues);
            allVenuesListAdapter.updateData(venues);
        }
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
        allVenuesRecyclarView.setVisibility(View.GONE);
    }
}
