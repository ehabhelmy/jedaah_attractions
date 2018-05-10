package com.spade.ja.ui.Home.profile.liked_directory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.explore.viewholder.VenuesViewHolder;

public class LikedDirectoryListAdapter extends BaseRecyclerViewAdapter<Venue> {

    private boolean big = false;
    public interface OnDirectoryAction {
        void onDirectoryLike(int id,String type);
        void onDirectoryClick(int id,String type);
    }

    private OnDirectoryAction onDirectoryAction;

    public void setOnDirectoryAction(OnDirectoryAction onDirectoryAction) {
        this.onDirectoryAction = onDirectoryAction;
    }

    public LikedDirectoryListAdapter(boolean big) {
        this.big = big;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (big){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_venues_card, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venus_card, parent, false);
        }
        return new VenuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data != null){
            VenuesViewHolder venuesViewHolder = (VenuesViewHolder) holder;
            venuesViewHolder.bind(data.get(position),onDirectoryAction);
        }else{
            //TODO : throw Exception
        }
    }
}
