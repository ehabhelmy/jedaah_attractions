package com.spade.ja.ui.Home.search.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.search.adapter.SearchListAdapter;
import com.spade.ja.util.DateTimeUtils;

import butterknife.BindView;

/**
 * Created by ehab on 3/26/18.
 */

public class SearchViewHolder extends BaseViewHolder<Result> {

    @BindView(R.id.smallCardView)
    CardView cardView;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.likeButton)
    LikeButton likeButton;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.cats)
    TextView cats;

    @BindView(R.id.eventDate)
    TextView eventDate;

    @BindView(R.id.location)
    ImageView location;

    public SearchViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Result baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {

    }

    @Override
    public void bind(Result baseModel, int position, TicketListener ticketListener) {

    }

    public void bind(Result baseModel, SearchListAdapter.onItemClick onItemClick){
        title.setText(baseModel.getTitle());
        Glide.with(image.getContext()).load(baseModel.getImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                image.setBackground(resource);
            }
        });
        likeButton.setLiked(baseModel.getIsLiked());
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                onItemClick.onLikeClicked(baseModel.getId(),baseModel.getType());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                onItemClick.onLikeClicked(baseModel.getId(),baseModel.getType());
            }
        });
        cardView.setOnClickListener(view -> {
            onItemClick.onCardClicked(baseModel.getId(),baseModel.getType());
        });

        if (baseModel.getAddress() != null) {
            cats.setText(baseModel.getAddress());
            setupDate(baseModel);
        }else {
            eventDate.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            setupCats(baseModel);
        }
    }

    private void setupDate(Result baseModel) {
        StringBuilder date = new StringBuilder();
        date.append(DateTimeUtils.getDay(DateTimeUtils.convertJSONDateToDate(baseModel.getStartDate())));
        date.append(" ");
        date.append(DateTimeUtils.getMonth(DateTimeUtils.convertJSONDateToDate(baseModel.getStartDate())));
        date.append(",");
        date.append(DateTimeUtils.getDaysRemaining(DateTimeUtils.convertJSONDateToDate(baseModel.getStartDate())));
        eventDate.setText(date.toString());
    }

    private void setupCats(Result baseModel) {
        StringBuilder cat = new StringBuilder();
        for (int i = 0; i < baseModel.getCategories().size(); i++) {
            cat.append(baseModel.getCategories().get(i) != null ? baseModel.getCategories().get(i) : "");
            try {
                cat.append(" | ");
                cat.append(baseModel.getSubCategories().get(i));
            } catch (Exception e) {
                cat.append("");
            } finally {
                if (i != baseModel.getCategories().size() - 1) {
                    cat.append(" , ");
                }
            }
        }
        cats.setText(cat.toString());
    }
}
