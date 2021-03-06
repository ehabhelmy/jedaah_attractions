package com.spade.ja.ui.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

import butterknife.ButterKnife;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class BaseViewHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public abstract void bind(M baseModel, RecyclerViewItemListener.onViewListener onViewListener,RecyclerViewItemListener.onFavouriteListener onFavouriteListener);
    public abstract void bind(M baseModel, int position, TicketListener ticketListener);

}
