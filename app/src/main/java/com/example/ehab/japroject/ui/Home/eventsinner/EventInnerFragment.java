package com.example.ehab.japroject.ui.Home.eventsinner;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.Data;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.pojo.EventDetails;
import com.like.LikeButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/20/17.
 */

public class EventInnerFragment extends BaseFragment implements EventInnerContract.View {

    @BindView(R.id.imageViewPager)
    ViewPager imageViewPager;

    @BindView(R.id.favourite)
    LikeButton likeButton;

    @BindView(R.id.eventMonth)
    TextView eventMonth;

    @BindView(R.id.eventDay)
    TextView eventDay;

    @BindView(R.id.eventDayRemaining)
    TextView dayRemaining;

    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @BindView(R.id.categories)
    TextView categories;

    @BindView(R.id.likes)
    TextView likes;

    @BindView(R.id.eventAddress)
    TextView eventAddress;

    @BindView(R.id.directions)
    TextView directions;

    @BindView(R.id.eventDate)
    TextView eventDate;

    @BindView(R.id.eventSchedule)
    LinearLayout eventSchedule;

    @BindView(R.id.socialMedia)
    RecyclerView socialMedia;

    @BindView(R.id.tags)
    GridView tags;

    @BindView(R.id.eventDescription)
    TextView eventDescription;

    @BindView(R.id.eventPrice)
    TextView eventPrice;

    @Inject
    EventInnerPresenter presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        directions.setPaintFlags(directions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.directions)
    void openMapsInNavigationMode() {

    }

    @OnClick(R.id.buyNow)
    void openbuyView(){

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
        return R.layout.fragment_events_inner;
    }

    @Override
    public void setupEventsInner(EventDetails data) {


    }
}
