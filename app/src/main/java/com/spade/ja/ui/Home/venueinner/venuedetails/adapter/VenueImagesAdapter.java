package com.spade.ja.ui.Home.venueinner.venuedetails.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.spade.ja.R;
import com.spade.ja.ui.widget.ImageLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ehab on 1/23/18.
 */

public class VenueImagesAdapter extends RecyclerView.Adapter<VenueImagesAdapter.VenueImagesViewHolder>{

    private ArrayList<String> imageURLS;
    private onPhotoClick onPhotoClick;

    public void setOnPhotoClick(VenueImagesAdapter.onPhotoClick onPhotoClick) {
        this.onPhotoClick = onPhotoClick;
    }

    public interface onPhotoClick {
        void onPhotoClicked(String imageUrl);
    }

    @Override
    public VenueImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_photos_layout,parent,false);
        return new VenueImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueImagesViewHolder holder, int position) {
        Glide.with(holder.getImageLayout().getContext()).load(imageURLS.get(position)).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                holder.getImageLayout().setBackground(resource);
            }
        });
        holder.getImageLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPhotoClick.onPhotoClicked(imageURLS.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageURLS.size();
    }

    public class VenueImagesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.venuePhoto)
        ImageLayout imageLayout;
        public VenueImagesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(VenueImagesViewHolder.this,itemView);
        }

        public ImageLayout getImageLayout() {
            return imageLayout;
        }
    }

    public void setImageURLS(ArrayList<String> imageURLS) {
        this.imageURLS = imageURLS;
    }


}
