package com.spade.ja.ui.Home.attractioninner.AttractionOrder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.viewtickets.Addon;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.viewholder.AddOnViewHolder;

/**
 * Created by ehab on 3/17/18.
 */

public class AddOnsAdapter extends BaseRecyclerViewAdapter<Addon> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attraction_ticket_type, parent, false);
        return new AddOnViewHolder(view);
    }
}
