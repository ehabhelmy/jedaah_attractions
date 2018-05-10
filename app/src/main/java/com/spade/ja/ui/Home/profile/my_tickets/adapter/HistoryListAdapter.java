package com.spade.ja.ui.Home.profile.my_tickets.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.spade.ja.ui.Home.profile.my_tickets.viewholder.HistoryViewHolder;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryListAdapter extends BaseRecyclerViewAdapter<HistoryEventsUi>{

    private HistoryListener ticketListener;

    public interface HistoryListener {
        void onUpcomingCancelClick(String type,int id);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (data.get(viewType).isUpOrPast()){
            if (data.get(viewType).getType().equals("event")){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view, parent, false);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view_attraction, parent, false);
            }
        }else {
            if (data.get(viewType).getType().equals("event")){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view_past, parent, false);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_card_view_past_attraction, parent, false);
            }
        }
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data != null){
            HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
            historyViewHolder.bind(data.get(position),position,ticketListener);
        }else{
            //TODO : throw Exception
        }
    }

    public void setTicketListener(HistoryListener ticketListener) {
        this.ticketListener = ticketListener;
    }
}
