package com.example.ehab.japroject.ui.Base.listener;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;

/**
 * Created by ehab on 12/1/17.
 */

public interface RecyclerViewItemListener {

    @FunctionalInterface
    interface onViewListener {
        void onViewClicked(BaseModel model);
    }

    @FunctionalInterface
    interface onFavouriteListener {
        void onFavouriteClicked(BaseModel model);
    }

}
