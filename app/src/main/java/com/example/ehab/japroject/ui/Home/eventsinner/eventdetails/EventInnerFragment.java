package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails;

import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.ImagePagerAdapter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.TagAdapter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.SocialMediaAdapter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.pojo.EventDetails;
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

    private Double latitude,longitude;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        directions.setPaintFlags(directions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.directions)
    void openMapsInNavigationMode() {

    }

    @OnClick(R.id.buyNow)
    void openBuyView(){

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
        latitude = data.getLatitude();
        longitude = data.getLongitude();
        eventTitle.setText(data.getEventTitle());
        eventMonth.setText(data.getEventMonth());
        eventDay.setText(data.getEventDay());
        dayRemaining.setText(data.getEventDatRemaining());
        categories.setText(data.getCategoriesText());
        likeButton.setLiked(data.isIsliked());
        likes.setText(data.getInterested());
        eventAddress.setText(data.getEventAddress());
        eventDate.setText(data.getEventDatetitle());
        eventPrice.setText(data.getEventPrice());
        eventDescription.setText(data.getEventDescription());
        for (String date : data.getEventDateDays()) {
            eventSchedule.addView(createTextView(date));
        }
        SocialMediaAdapter adapter = new SocialMediaAdapter();
        adapter.setData(data.getSocialMedia());
        socialMedia.setAdapter(adapter);
        TagAdapter tagAdapter = new TagAdapter(this.getContext());
        tags.setAdapter(tagAdapter);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this.getContext());
        imagePagerAdapter.setImageURLS(data.getImageURLS());
        imageViewPager.setAdapter(imagePagerAdapter);
    }

    private TextView createTextView(String day) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,convertFromDpToPixel());
        TextView dDay = new TextView(this.getContext());
        dDay.setText(day);
        dDay.setLayoutParams(params);
        dDay.setTextSize(getResources().getDimension(R.dimen.cardsubtitle));
        return dDay;
    }

    private int convertFromDpToPixel(){
        Resources r = this.getContext().getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                2,
                r.getDisplayMetrics()
        );
        return px;
    }
}
