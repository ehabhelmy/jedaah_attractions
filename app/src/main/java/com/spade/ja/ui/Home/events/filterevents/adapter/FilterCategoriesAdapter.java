package com.spade.ja.ui.Home.events.filterevents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;

import java.util.ArrayList;

/**
 * Created by ehab on 2/18/18.
 */

public class FilterCategoriesAdapter extends BaseAdapter {

    private ArrayList<Cats> cats;

    private Context context;

    public FilterCategoriesAdapter(Context context) {
        this.context = context;
    }

    public void setCats(ArrayList<Cats> cats) {
        this.cats = cats;
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Object getItem(int position) {
        return cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            view = LayoutInflater.from(context).inflate(R.layout.tag_view,parent,false);

        } else {
            view = convertView;
        }
        ImageView catIcon = view.findViewById(R.id.tagIcon);
        TextView catName = view.findViewById(R.id.tagType);
        Glide.with(context).load(cats.get(position).getIcon()).apply(new RequestOptions().placeholder(R.drawable.ca_food_ic).error(R.drawable.ca_food_ic)).into(catIcon);
        catName.setText(cats.get(position).getName());
        return view;
    }
}
