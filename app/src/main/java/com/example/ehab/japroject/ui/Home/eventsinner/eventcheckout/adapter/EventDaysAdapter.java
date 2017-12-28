package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTag;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.OrderEventDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ehab on 12/27/17.
 */

public class EventDaysAdapter extends BaseAdapter {

    private ArrayList<OrderEventDay> days;

    private Context context;

    public EventDaysAdapter(Context context) {
        this.context = context;
    }

    public void setDays(ArrayList<OrderEventDay> days) {
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.eventday_view,parent,false);

        } else {
            view = convertView;
        }
        RelativeLayout container = view.findViewById(R.id.eventDayContainer);
        TextView eventMonth = view.findViewById(R.id.eventMonth);
        TextView eventDay = view.findViewById(R.id.eventDay);
        TextView eventTime = view.findViewById(R.id.eventTime);
        eventMonth.setText(days.get(position).getMonth());
        eventDay.setText(days.get(position).getDay());
        eventTime.setText(days.get(position).getTime());
        return view;
    }
}
