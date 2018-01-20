package com.example.ehab.japroject.ui.Home.directory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.allvenues.Venue;
import com.example.ehab.japroject.datalayer.pojo.response.venues.Datum;

import java.util.List;

/**
 * Created by Roma on 1/19/2018.
 */

public class AllVenuesAdapter extends BaseAdapter {

    private Context context;
    private List<Venue> datumArrayList;

    public AllVenuesAdapter(Context context, List<Venue> datumArrayList) {
        this.context = context;
        this.datumArrayList = datumArrayList;
    }

    @Override
    public int getCount() {
        return datumArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return datumArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.all_venues_card, parent, false);
        return view;
    }
}
