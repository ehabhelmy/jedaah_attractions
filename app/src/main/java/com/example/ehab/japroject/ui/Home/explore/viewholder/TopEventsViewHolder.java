package com.example.ehab.japroject.ui.Home.explore.viewholder;

import android.view.View;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.Datum;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;

import butterknife.BindView;

/**
 * Created by ehab on 12/11/17.
 */

public class TopEventsViewHolder extends BaseViewHolder<Datum> {

    public TopEventsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Datum BaseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {

    }
}
