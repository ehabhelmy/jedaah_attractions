package com.spade.ja.ui.Home.events.all_events.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.events.all_events.listener.OnLoadMoreListener;
import com.spade.ja.ui.Home.events.all_events.viewholder.LoadingViewHolder;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.ui.Home.explore.viewholder.EventsViewHolder;

/**
 * Created by ehab on 1/1/18.
 */

public class AllEventsListAdapter extends BaseRecyclerViewAdapter<Event> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;
    private int visibleThreshold = 6;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;

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
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_cardview, parent, false);
            return new EventsViewHolder(view);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_progress_bar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    public void setLoading(boolean flag) {
        isLoading = flag;
    }
}
