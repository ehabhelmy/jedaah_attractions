package com.spade.ja.ui.Home.events.all_events.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.ui.widget.ImageLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by ehab on 1/1/18.
 */

public class AllEventsViewHolder extends BaseViewHolder<Event>{

    @BindView(R.id.eventCardView)
    CardView eventContainer;

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

    public AllEventsViewHolder(View itemView) {
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
        favourite.setLiked(baseModel.isLiked());
        Picasso.with(eventImage.getContext()).load(baseModel.getEventImage()).placeholder(R.mipmap.myimage).error(R.mipmap.myimage).into(eventImage);
        favourite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                onFavouriteListener.onFavouriteClicked(baseModel.getId());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                onFavouriteListener.onFavouriteClicked(baseModel.getId());
            }
        });
        eventContainer.setOnClickListener(v -> {
            onViewListener.onViewClicked(baseModel.getId());
        });
    }

    @Override
    public void bind(Event baseModel, int position, TicketListener ticketListener) {

    }
}
