package com.example.ehab.japroject.ui.Home.profile.my_tickets.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseRecyclerViewAdapter;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.viewholder.TicketViewHolder;
import com.example.ehab.japroject.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.example.ehab.japroject.ui.Home.profile.my_tickets.viewholder.HistoryViewHolder;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryListAdapter extends BaseRecyclerViewAdapter<HistoryEventsUi>{

    private TicketListener ticketListener;
    private String type;

    public enum HistoryType {
        UPCOMING,PAST
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (type.equals(HistoryType.UPCOMING.name())) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view_past, parent, false);
        }
        return new HistoryViewHolder(view);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
