package com.spade.ja.ui.Home.explore.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.profile.liked_directory.adapter.LikedDirectoryListAdapter;
import com.spade.ja.ui.widget.ImageLayout;
import com.like.LikeButton;
import com.like.OnLikeListener;

import butterknife.BindView;

/**
 * Created by ehab on 1/19/18.
 */

public class VenuesViewHolder extends BaseViewHolder<Venue> {

    @BindView(R.id.venuImage)
    ImageLayout venueImage;

    @BindView(R.id.venueContainer)
    LinearLayout venueContainer;

    @BindView(R.id.sponsored)
    FrameLayout sponsored;

    @BindView(R.id.favourite)
    LikeButton favourite;

    @BindView(R.id.venueName)
    TextView venuName;

    @BindView(R.id.cats)
    TextView cats;

    public VenuesViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Venue baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        if (baseModel.getIsSponsored() != 1) {
            sponsored.setVisibility(View.GONE);
        }
        venuName.setText(baseModel.getTitle());
        StringBuilder cat = new StringBuilder();
        for (int i = 0 ; i < baseModel.getCategories().size() ; i++ ) {
            cat.append(baseModel.getCategories().get(i) != null ? baseModel.getCategories().get(i):"");
            try {
                cat.append(" | ");
                cat.append(baseModel.getSubCategories().get(i));
            }catch (Exception e){
                cat.delete(cat.length()-3,cat.length() -1);
                cat.append("");
            }finally {
                if (i != baseModel.getCategories().size() - 1) {
                    cat.append(" , ");
                }
            }
        }
        cats.setText(cat);
        Glide.with(venueImage.getContext()).load(baseModel.getImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                venueImage.setBackground(resource);
            }
        });
        favourite.setLiked(baseModel.getIsLiked());
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
        venueContainer.setOnClickListener(view -> {
            onViewListener.onViewClicked(baseModel.getId());
        });
    }

    public void bind(Venue baseModel, LikedDirectoryListAdapter.OnDirectoryAction onDirectoryAction) {
        if (baseModel.getIsSponsored() != 1) {
            sponsored.setVisibility(View.GONE);
        }
        venuName.setText(baseModel.getTitle());
        StringBuilder cat = new StringBuilder();
        for (int i = 0 ; i < baseModel.getCategories().size() ; i++ ) {
            cat.append(baseModel.getCategories().get(i) != null ? baseModel.getCategories().get(i):"");
            try {
                cat.append(" | ");
                cat.append(baseModel.getSubCategories().get(i));
            }catch (Exception e){
                cat.append("");
            }finally {
                if (i != baseModel.getCategories().size() - 1) {
                    cat.append(" , ");
                }
            }
        }
        cats.setText(cat);
        Glide.with(venueImage.getContext()).load(baseModel.getImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                venueImage.setBackground(resource);
            }
        });
        favourite.setLiked(baseModel.getIsLiked());
        favourite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                onDirectoryAction.onDirectoryLike(baseModel.getId(),baseModel.getType());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                onDirectoryAction.onDirectoryLike(baseModel.getId(),baseModel.getType());
            }
        });
        venueContainer.setOnClickListener(view -> {
            onDirectoryAction.onDirectoryClick(baseModel.getId(),baseModel.getType());
        });
    }

    @Override
    public void bind(Venue baseModel, int position, TicketListener ticketListener) {

    }
}
