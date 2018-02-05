package com.spade.ja.ui.Home.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Home.explore.viewholder.CategoryViewHolder;

/**
 * Created by Romisaa.Attia on 12/12/2017.
 */

public class CategoryListAdapter extends BaseRecyclerViewAdapter<Cats> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(view);
    }
}
