package com.spade.ja.ui.Home.events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterCategoriesChoosenAdapter extends RecyclerView.Adapter<FilterCategoriesChoosenAdapter.ViewHolder> {

    public interface OnCatUnSelected {
        void onCategoryUnSelected(Cats cats);
    }

    private FilterCategoriesChoosenAdapter.OnCatUnSelected onCatSelected;

    public void setOnCatUnSelected(FilterCategoriesChoosenAdapter.OnCatUnSelected onCatSelected) {
        this.onCatSelected = onCatSelected;
    }

    private ArrayList<Cats> cats;

    private Context context;

    public FilterCategoriesChoosenAdapter(Context context) {
        this.context = context;
    }

    public void setCats(ArrayList<Cats> cats) {
        this.cats = cats;
    }

    public ArrayList<Cats> getCats() {
        return cats;
    }

    @NonNull
    @Override
    public FilterCategoriesChoosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilterCategoriesChoosenAdapter.ViewHolder viewHolder = new FilterCategoriesChoosenAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.filter_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCategoriesChoosenAdapter.ViewHolder holder, int position) {
        Cats category = cats.get(position);
        Glide.with(context).load(category.getIcon()).apply(new RequestOptions().placeholder(R.drawable.ca_food_ic).error(R.drawable.ca_food_ic)).into(holder.tagIcon);
        holder.tagType.setText(category.getName());
        holder.view.setOnClickListener(v -> {
            cats.remove(category);
            notifyDataSetChanged();
            onCatSelected.onCategoryUnSelected(category);
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
