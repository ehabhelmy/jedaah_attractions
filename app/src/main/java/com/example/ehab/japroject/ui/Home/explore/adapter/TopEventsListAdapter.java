package com.example.ehab.japroject.ui.Home.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.explore.viewholder.TopEventsViewHolder;

/**
 * Created by ehab on 12/11/17.
 */

public class TopEventsListAdapter extends BaseRecyclerViewAdapter<Datum> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new TopEventsViewHolder(view);
    }
}
