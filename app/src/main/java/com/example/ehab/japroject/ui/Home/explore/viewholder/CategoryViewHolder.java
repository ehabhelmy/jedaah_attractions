package com.example.ehab.japroject.ui.Home.explore.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by Romisaa.Attia on 12/13/2017.
 */

public class CategoryViewHolder extends BaseViewHolder<Cats> {

    @BindView(R.id.category_image)
    ImageView categoryImage;

    @BindView(R.id.category_name)
    TextView categoryName;

    public CategoryViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Cats baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        categoryName.setText(baseModel.getName());
        Glide.with(categoryImage.getContext()).load(baseModel.getIcon()).apply(new RequestOptions().placeholder(R.drawable.ca_cafe_ic).error(R.drawable.ca_cafe_ic)).into(categoryImage);
    }

    @Override
    public void bind(Cats baseModel, int position, TicketListener ticketListener) {

    }
}