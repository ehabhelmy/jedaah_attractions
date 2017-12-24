package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails;

import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @BindView(R.id.imageViewPager)
    ViewPager imageViewPager;

    @Nullable
    @BindView(R.id.favourite)
    LikeButton likeButton;

    @Nullable
    @BindView(R.id.eventMonth)
    TextView eventMonth;

    @Nullable
    @BindView(R.id.eventDay)
    TextView eventDay;

    @Nullable
    @BindView(R.id.eventDayRemaining)
    TextView dayRemaining;

    @Nullable
    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @Nullable
    @BindView(R.id.categories)
    TextView categories;

    @Nullable
    @BindView(R.id.likes)
    TextView likes;

    @Nullable
    @BindView(R.id.eventAddress)
    TextView eventAddress;

    @Nullable
    @BindView(R.id.directions)
    TextView directions;

    @Nullable
    @BindView(R.id.eventDate)
    TextView eventDate;

    @Nullable
    @BindView(R.id.eventSchedule)
    LinearLayout eventSchedule;

    @Nullable
    @BindView(R.id.socialMedia)
    RecyclerView socialMedia;

    @Nullable
    @BindView(R.id.tags)
    GridView tags;

    @Nullable
    @BindView(R.id.eventDescription)
    TextView eventDescription;

    @Nullable
    @BindView(R.id.eventPrice)
    TextView eventPrice;

    @Inject
    EventInnerPresenter presenter;

    private double latitude,longitude;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        directions.setPaintFlags(directions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.directions)
    void openMapsInNavigationMode() {
        presenter.openNavigationView(latitude,longitude);
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
        latitude = data.getLatitude().doubleValue();
        longitude = data.getLongitude().doubleValue();
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
        socialMedia.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        socialMedia.setAdapter(adapter);
        TagAdapter tagAdapter = new TagAdapter(this.getContext());
        tagAdapter.setTags(data.getEventTags());
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
        float denisty = getResources().getDisplayMetrics().density;
        float textSize = getResources().getDimension(R.dimen.cardsubtitle) / denisty;
        dDay.setTextSize(textSize);
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
