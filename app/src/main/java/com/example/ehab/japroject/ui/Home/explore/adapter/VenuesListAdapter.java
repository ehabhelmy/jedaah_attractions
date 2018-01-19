package com.example.ehab.japroject.ui.Home.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Datum;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.explore.viewholder.EventsViewHolder;
import com.example.ehab.japroject.ui.Home.explore.viewholder.VenuesViewHolder;

/**
 * Created by ehab on 1/19/18.
 */

public class VenuesListAdapter extends BaseRecyclerViewAdapter<Datum> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venus_card, parent, false);
        return new VenuesViewHolder(view);
    }
}
