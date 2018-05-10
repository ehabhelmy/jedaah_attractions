package com.spade.ja.ui.Home.venueinner.venuedetails;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;
import com.spade.ja.datalayer.pojo.response.venuesinner.Data;
import com.spade.ja.datalayer.pojo.response.venuesinner.VenueOpeningHour;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.eventsinner.eventdetails.adapter.SocialMediaAdapter;
import com.spade.ja.ui.Home.venueinner.venuedetails.adapter.VenueImagesAdapter;
import com.spade.ja.ui.widget.ImageLayout;
import com.spade.ja.util.Constants;
import com.spade.ja.util.DateTimeUtils;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 1/20/18.
 */

public class VenueDetailsFragment extends BaseFragment implements VenueDetailsContract.View {

    @Inject
    VenueDetailsPresenter presenter;

    @Nullable
    @BindView(R.id.venueInnerImage)
    ImageLayout venueImage;

    @Nullable
    @BindView(R.id.favourite)
    LikeButton likeButton;

    @Nullable
    @BindView(R.id.sponsored)
    FrameLayout sponsored;

    @Nullable
    @BindView(R.id.venueTitle)
    TextView venueTitle;

    @Nullable
    @BindView(R.id.categories)
    TextView cats;

    @Nullable
    @BindView(R.id.venueAddress)
    TextView venueAddress;

    @Nullable
    @BindView(R.id.directions)
    TextView directions;

    @Nullable
    @BindView(R.id.openNow)
    TextView openNow;

    @Nullable
    @BindView(R.id.venueOpenDays)
    LinearLayout venueOpenDays;

    @Nullable
    @BindView(R.id.socialMedia)
    RecyclerView socialMedia;

    @Nullable
    @BindView(R.id.venueDescription)
    TextView description;

    @Nullable
    @BindView(R.id.venuesPhotos)
    RecyclerView venuePhotos;

    @Nullable
    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @Nullable
    @BindView(R.id.eventInnerContainer)
    ScrollView scrollView;

    private double latitude,longitude;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        directions.setPaintFlags(directions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.directions)
    void openMapsInNavigationMode() {
        presenter.openNavigationView(latitude,longitude);
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
        loadingView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
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
        return R.layout.fragment_venue_inner;
    }

    @Override
    public void setupVenueDetails(Data data) {
        latitude = Double.parseDouble(data.getLat());
        longitude = Double.parseDouble(data.getLng());
        venueTitle.setText(data.getTitle());
        venueAddress.setText(data.getAddress());
        StringBuilder cat = new StringBuilder();
        for (int i = 0 ; i < data.getCategories().size() ; i++ ) {
            cat.append(data.getCategories().get(i) != null ? data.getCategories().get(i):"");
            try {
                cat.append(" | ");
                cat.append(data.getSubCategories().get(i));
            }catch (Exception e){
                cat.append("");
            }finally {
                if (i != data.getCategories().size() - 1) {
                    cat.append(" , ");
                }
            }
        }
        if (data.getIsSponsored() !=1) {
            sponsored.setVisibility(View.GONE);
        }
        cats.setText(cat);
        Glide.with(venueImage.getContext()).load(data.getImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                venueImage.setBackground(resource);
            }
        });
        likeButton.setLiked(data.getIsLiked());
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                presenter.like();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                presenter.like();
            }
        });
        description.setText(data.getDescription());
        StringBuilder venuDays = new StringBuilder();
        StringBuilder hours = new StringBuilder();
        hours.append("Hours ");
        for (VenueOpeningHour venueOpeningHour:data.getVenueOpeningHours()) {
            if (venueOpeningHour.getIsClosed() == 1) {
                venuDays.append(venueOpeningHour.getVenueDay()+", ");
            }
            if (DateTimeUtils.isOpenNow(venueOpeningHour.getVenueDay())){
                if (venueOpeningHour.getIsClosed() == 1) {
                    openNow.setText("Closed Now");
                }
                hours.append(venueOpeningHour.getStartTime() +" - "+venueOpeningHour.getEndTime());
            }
        }
        venuDays.append("Closed");
        venueOpenDays.addView(createTextView(hours.toString()));
        venueOpenDays.addView(createTextView(venuDays.toString()));
        SocialMediaAdapter adapter = new SocialMediaAdapter();
        ArrayList<SocialMedium> socialMediumArrayList = (ArrayList<SocialMedium>) data.getSocialMedia();
        if (data.getWebsite() != null){
            socialMediumArrayList.add(new SocialMedium(0,data.getWebsite(),"WEBSITE"));
        }
        if (data.getTelephoneNumbers() !=null){
            socialMediumArrayList.add(new SocialMedium(0,data.getTelephoneNumbers(),"PHONE"));
        }
        adapter.setData(socialMediumArrayList);
        socialMedia.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        socialMedia.setAdapter(adapter);
        VenueImagesAdapter venueImagesAdapter = new VenueImagesAdapter();
        venueImagesAdapter.setImageURLS((ArrayList<String>) data.getGallery());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
        venuePhotos.setLayoutManager(layoutManager);
        venuePhotos.addItemDecoration(dividerItemDecoration);
        venueImagesAdapter.setOnPhotoClick(imageUrl -> {
            presenter.showFullScreenPhoto(imageUrl,data.getTitle());
        });
        venuePhotos.setAdapter(venueImagesAdapter);
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
