package com.spade.ja.ui.Home.eventsinner.eventdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.R;

import java.util.ArrayList;

/**
 * Created by ehab on 12/22/17.
 */

public class ImagePagerAdapter extends PagerAdapter {

    private ArrayList<String> imageURLS;

    private Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    public void setImageURLS(ArrayList<String> imageURLS) {
        this.imageURLS = imageURLS;
    }

    @Override
    public int getCount() {
        return imageURLS.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context).load(imageURLS.get(position)).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
