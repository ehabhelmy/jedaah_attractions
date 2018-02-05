package com.spade.ja.ui.Base.listener;

/**
 * Created by ehab on 12/1/17.
 */

public interface RecyclerViewItemListener {

    @FunctionalInterface
    interface onViewListener {
        void onViewClicked(int id);
    }

    @FunctionalInterface
    interface onFavouriteListener {
        void onFavouriteClicked(int id);
    }

}
