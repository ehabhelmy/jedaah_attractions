package com.spade.ja.ui.Home.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.search.viewholder.SearchViewHolder;

/**
 * Created by ehab on 3/26/18.
 */

public class SearchListAdapter extends BaseRecyclerViewAdapter<Result> {

    public interface onItemClick {
        void onLikeClicked(int id,String type);
        void onCardClicked(int id,String type);
    }

    private onItemClick onItemClick;

    public SearchListAdapter.onItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(SearchListAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data != null){
            SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
            searchViewHolder.bind(data.get(position),onItemClick);
        }else{
            //TODO : throw Exception
        }
    }
}
