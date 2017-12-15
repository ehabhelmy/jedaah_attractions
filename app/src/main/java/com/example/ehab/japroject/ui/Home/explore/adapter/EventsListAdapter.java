package com.example.ehab.japroject.ui.Home.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;
import com.example.ehab.japroject.ui.Home.explore.viewholder.EventsViewHolder;

/**
 * Created by ehab on 12/11/17.
 */

public class EventsListAdapter extends BaseRecyclerViewAdapter<Event> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new EventsViewHolder(view);
    }
}
