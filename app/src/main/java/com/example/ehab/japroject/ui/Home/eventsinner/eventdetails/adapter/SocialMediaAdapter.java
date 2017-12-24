package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.viewholder.SocialMediaViewHolder;

/**
 * Created by ehab on 12/22/17.
 */

public class SocialMediaAdapter extends BaseRecyclerViewAdapter<SocialMedium> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_media, parent, false);
        return new SocialMediaViewHolder(view);
    }
}
