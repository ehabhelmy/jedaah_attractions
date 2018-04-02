package com.spade.ja.ui.Home.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.explore.viewholder.VenuesViewHolder;

/**
 * Created by ehab on 1/19/18.
 */

public class VenuesListAdapter extends BaseRecyclerViewAdapter<Venue> {

    private boolean big = false;

    public VenuesListAdapter(boolean big) {
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
}
