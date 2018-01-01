package com.example.ehab.japroject.ui.Home.events.all_events.viewholder;

import android.view.View;
import android.widget.ProgressBar;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

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
