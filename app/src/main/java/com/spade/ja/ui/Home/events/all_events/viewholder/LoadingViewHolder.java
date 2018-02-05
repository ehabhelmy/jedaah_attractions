package com.spade.ja.ui.Home.events.all_events.viewholder;

import android.view.View;
import android.widget.ProgressBar;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

import butterknife.BindView;

/**
 * Created by ehab on 1/1/18.
 */

public class LoadingViewHolder extends BaseViewHolder {

    @BindView(R.id.progressBar1)
    ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BaseModel baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        progressBar.setIndeterminate(true);
    }

    @Override
    public void bind(BaseModel baseModel, int position, TicketListener ticketListener) {

    }
}
