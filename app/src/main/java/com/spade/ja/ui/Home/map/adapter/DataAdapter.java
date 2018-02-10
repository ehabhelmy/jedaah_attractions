package com.spade.ja.ui.Home.map.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.map.Data;
import com.spade.ja.ui.Home.map.holder.DataHolder;
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


