package com.example.ehab.japroject.ui.map.holder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Venue;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.example.ehab.japroject.ui.map.Data;
import com.like.LikeButton;
import com.like.OnLikeListener;

import butterknife.BindView;

/**
 * Created by Roma on 2/3/2018.
 */

public class DataHolder extends BaseViewHolder<Data> {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.likeButton)
    LikeButton likeButton;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.cats)
    TextView cats;

    public DataHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Data baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {

        title.setText(baseModel.getTitle());
        Glide.with(image.getContext()).load(baseModel.getImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                image.setBackground(resource);
            }
        });
        cats.setText(baseModel.getDescription());
    }

    @Override
    public void bind(Data baseModel, int position, TicketListener ticketListener) {

    }


}
