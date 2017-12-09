package com.example.ehab.japroject.ui.Base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;

import java.util.ArrayList;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class BaseRecyclerViewAdapter<M extends BaseModel> extends RecyclerView.Adapter<BaseViewHolder>{

    private RecyclerViewItemListener.onViewListener onViewListener;
    private RecyclerViewItemListener.onFavouriteListener onFavouriteListener;
    private ArrayList<M> data;

    @Override
    public  abstract BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (data != null){
            holder.bind(data.get(position),onViewListener,onFavouriteListener);
        }else{
            //TODO : throw Exception
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnViewListener(RecyclerViewItemListener.onViewListener onViewListener) {
        this.onViewListener = onViewListener;
    }

    public void setOnFavouriteListener(RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        this.onFavouriteListener = onFavouriteListener;
    }

    public ArrayList<M> getData() {
        return data;
    }

    public void setData(ArrayList<M> data) {
        this.data = data;
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<M> data){
        this.data = data;
        notifyDataSetChanged();
    }
}
