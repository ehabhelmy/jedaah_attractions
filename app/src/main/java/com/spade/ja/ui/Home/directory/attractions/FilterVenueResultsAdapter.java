package com.spade.ja.ui.Home.directory.attractions;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allvenues.Venue;
import com.spade.ja.datalayer.pojo.response.filter.venues.Result;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.directory.venues.viewholder.AllVenuesViewHolder;
import com.spade.ja.ui.Home.events.all_events.listener.OnLoadMoreListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.widget.ImageLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterVenueResultsAdapter extends RecyclerView.Adapter<FilterVenueResultsAdapter.ViewHolder> {

    private List<Result> dataList;
    private RecyclerViewItemListener.onViewListener itemListener;
    private RecyclerViewItemListener.onFavouriteListener favouriteListener;

    public void setItemListener(RecyclerViewItemListener.onViewListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setFavouriteListener(RecyclerViewItemListener.onFavouriteListener favouriteListener) {
        this.favouriteListener = favouriteListener;
    }

    public void setDataList(List<Result> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_venues_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result data = dataList.get(position);
        holder.bind(data, itemListener, favouriteListener);
    }

    @Override
    public int getItemCount() {
        return !dataList.isEmpty() ? dataList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Result baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
            if (baseModel.getIsSponsored() != 1) {
                sponsored.setVisibility(View.GONE);
            }
            venuName.setText(baseModel.getTitle());
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
    }
}