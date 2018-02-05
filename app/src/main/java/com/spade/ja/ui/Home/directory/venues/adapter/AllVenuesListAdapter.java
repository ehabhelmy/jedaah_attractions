package com.spade.ja.ui.Home.directory.venues.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allvenues.Venue;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.directory.venues.viewholder.AllVenuesViewHolder;
import com.spade.ja.ui.Home.events.all_events.listener.OnLoadMoreListener;

/**
 * Created by Roma on 1/22/2018.
 */


public class AllVenuesListAdapter extends BaseRecyclerViewAdapter<Venue> {

    private OnLoadMoreListener onLoadMoreListener;
    private int lastVisibleItemPosition, totalItemCount;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_venues_card, parent, false);
        return new AllVenuesViewHolder(view);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setupLoadingRecyclerView(RecyclerView recyclerView) {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }


}
