package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTicket;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.TicketDate;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.viewholder.TicketViewHolder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.viewholder.SocialMediaViewHolder;

/**
 * Created by ehab on 12/28/17.
 */

public class EventTicketsAdapter extends BaseRecyclerViewAdapter<EventTicket> {

    private TicketListener ticketListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_view, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data != null){
            holder.bind(data.get(position),position,ticketListener);
        }else{
            //TODO : throw Exception
        }
    }

    public TicketListener getTicketListener() {
        return ticketListener;
    }

    public void setTicketListener(TicketListener ticketListener) {
        this.ticketListener = ticketListener;
    }
}