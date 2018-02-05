package com.spade.ja.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by ehab on 12/12/17.
 */

public class ImageLayout extends ConstraintLayout implements Target {

    public ImageLayout(Context context) {
        super(context);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        setBackground(new BitmapDrawable(getResources(), bitmap));
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        setBackground(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        setBackground(placeHolderDrawable);
    }
}
