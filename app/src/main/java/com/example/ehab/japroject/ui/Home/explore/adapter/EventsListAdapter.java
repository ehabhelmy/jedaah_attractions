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

    boolean big = false;

    public EventsListAdapter(boolean big) {
        this.big = big;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (!big) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_cardview, parent, false);
        }
        return new EventsViewHolder(view);
    }
}
