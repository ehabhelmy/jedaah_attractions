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
import com.example.ehab.japroject.ui.widget.ImageLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by ehab on 12/11/17.
 */

public class TopEventsViewHolder extends BaseViewHolder<Datum> {

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

    public TopEventsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Datum baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        eventName.setText(baseModel.getTitle());
        eventAddress.setText(baseModel.getAddress());
        Picasso.with(eventImage.getContext()).load(baseModel.getImages().get(0)).placeholder(R.mipmap.myimage).error(R.mipmap.myimage).into(eventImage);
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
