package com.example.ehab.japroject.ui.map.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Venue;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.map.Data;
import com.example.ehab.japroject.ui.map.holder.DataHolder;

/**
 * Created by Roma on 2/3/2018.
 */

public class DataAdapter extends BaseRecyclerViewAdapter<Data> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_card_view, parent, false);
        return new DataHolder(view);
    }
}


