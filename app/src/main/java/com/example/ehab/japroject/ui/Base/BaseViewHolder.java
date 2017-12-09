package com.example.ehab.japroject.ui.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class BaseViewHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(M BaseModel, RecyclerViewItemListener.onViewListener onViewListener,RecyclerViewItemListener.onFavouriteListener onFavouriteListener);
}
