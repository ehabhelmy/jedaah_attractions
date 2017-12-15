package com.example.ehab.japroject.ui.Home.explore.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.ui.widget.ImageLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by ehab on 12/11/17.
 */

public class EventsViewHolder extends BaseViewHolder<Event> {

    @BindView(R.id.eventImage)
    ImageLayout eventImage;

    @BindView(R.id.eventName)
    TextView eventName;

    @BindView(R.id.eventAddress)
    TextView eventAddress;

    @BindView(R.id.eventInterested)
    TextView eventInterested;

    @BindView(R.id.favourite)
    LikeButton favourite;

    @BindView(R.id.eventDay)
    TextView eventDay;

    @BindView(R.id.eventMonth)
    TextView eventMonth;

    @BindView(R.id.eventRemaining)
    TextView eventRemaining;

    public EventsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Event baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        eventName.setText(baseModel.getEventName());
        eventAddress.setText(baseModel.getEventAddress());
        eventInterested.setText(baseModel.getEventLikes());
        eventMonth.setText(baseModel.getEventMonth());
        eventDay.setText(baseModel.getEventDay());
        eventRemaining.setText(baseModel.getEventRemaining());
        Picasso.with(eventImage.getContext()).load(baseModel.getEventImage()).placeholder(R.mipmap.myimage).error(R.mipmap.myimage).into(eventImage);
        favourite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                onFavouriteListener.onFavouriteClicked(baseModel);
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });
    }
}
