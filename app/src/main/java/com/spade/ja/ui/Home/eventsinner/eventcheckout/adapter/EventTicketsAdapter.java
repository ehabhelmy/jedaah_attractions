package com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.EventTicket;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.viewholder.TicketViewHolder;

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