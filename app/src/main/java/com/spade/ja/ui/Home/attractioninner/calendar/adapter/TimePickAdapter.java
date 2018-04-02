package com.spade.ja.ui.Home.attractioninner.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;
import com.spade.ja.util.DateTimeUtils;

import java.util.List;

/**
 * Created by ehab on 3/14/18.
 */

public class TimePickAdapter extends BaseAdapter{

    private List<TimeModel> times;

    private Context context;

    public TimePickAdapter(Context context) {
        this.context = context;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }

    @Override
    public int getCount() {
        return times.size();
    }

    @Override
    public Object getItem(int position) {
        return times.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.tag_view_white,parent,false);

        } else {
            view = convertView;
        }
        ImageView catIcon = view.findViewById(R.id.tagIcon);
        catIcon.setVisibility(View.GONE);
        TextView catName = view.findViewById(R.id.tagType);
        catName.setText(DateTimeUtils.convertToTimeAm(times.get(position).getStartTime())+ " - " + DateTimeUtils.convertToTimeAm(times.get(position).getEndTime()));
        return view;
    }
}
