package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ehab on 12/22/17.
 */

public class TagAdapter extends BaseAdapter {

    private ArrayList<EventTag> tags;

    private Context context;

    public TagAdapter(Context context) {
        this.context = context;
    }

    public void setTags(ArrayList<EventTag> tags) {
        this.tags = tags;
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private int convertFromDpToPixel(){
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                r.getDisplayMetrics()
        );
        return px;
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
        ImageView tagIcon = view.findViewById(R.id.tagIcon);
        TextView tagName = view.findViewById(R.id.tagType);
        tagName.setText(tags.get(position).getName());
        view.setBackground(ContextCompat.getDrawable(context,R.drawable.rounded_rectangle_grey));
        view.setPadding(convertFromDpToPixel(),convertFromDpToPixel(),convertFromDpToPixel(),convertFromDpToPixel());
        Picasso.with(context).load(tags.get(position).getImage()).placeholder(R.drawable.ic_type_g).error(R.drawable.ic_type_g).into(tagIcon);
        return view;
    }
}
