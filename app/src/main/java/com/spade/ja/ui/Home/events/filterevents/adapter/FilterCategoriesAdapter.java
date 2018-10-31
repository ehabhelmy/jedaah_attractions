package com.spade.ja.ui.Home.events.filterevents.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ehab on 2/18/18.
 */

public class FilterCategoriesAdapter extends RecyclerView.Adapter<FilterCategoriesAdapter.ViewHolder> {

    public interface OnCatSelected {
        void onCategorySelected(Cats cats,View layout,ImageView icon,TextView type);
    }

    private OnCatSelected onCatSelected;

    public void setOnCatSelected(OnCatSelected onCatSelected) {
        this.onCatSelected = onCatSelected;
    }

    private ArrayList<Cats> cats;

    private Context context;

    public FilterCategoriesAdapter(Context context) {
        this.context = context;
    }

    public void setCats(ArrayList<Cats> cats) {
        this.cats = cats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.tag_view,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cats category = cats.get(position);
        Glide.with(context).load(category.getIcon()).apply(new RequestOptions().placeholder(R.drawable.ca_food_ic).error(R.drawable.ca_food_ic)).into(holder.tagIcon);
        holder.tagType.setText(category.getName());
        holder.view.setOnClickListener(v -> {
           onCatSelected.onCategorySelected(category,holder.view,holder.tagIcon,holder.tagType);
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tagIcon)
        ImageView tagIcon;

        @BindView(R.id.tagType)
        TextView tagType;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
