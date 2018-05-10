package com.spade.ja.ui.Home.venueinner.venuedetails.fullscreenphoto;

import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.spade.ja.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FullScreenPhotoFragment extends DialogFragment {

    private static final String IMAGEURL = "imageUrl";
    private static final String TITLE = "title";

    @BindView(R.id.close)
    ImageView close;

    @BindView(R.id.venueName)
    TextView venueName;

    @BindView(R.id.venuePhoto)
    ImageView venuePhoto;


    public static FullScreenPhotoFragment newInstance(String imageurl, String title){
        FullScreenPhotoFragment fullScreenPhotoFragment = new FullScreenPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGEURL,imageurl);
        bundle.putString(TITLE,title);
        fullScreenPhotoFragment.setArguments(bundle);
        return fullScreenPhotoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        Unbinder unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    private void setupView() {
        int width = getResources().getDimensionPixelSize(R.dimen.photo_width);
        int height = getResources().getDimensionPixelSize(R.dimen.photo_height);
        getDialog().getWindow().setLayout(width, height);
        if (getArguments() != null){
            venueName.setText(getArguments().getString(TITLE));
            Glide.with(getActivity()).load(getArguments().getString(IMAGEURL)).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    venuePhoto.setImageDrawable(resource);
                }
            });
        }
        close.setOnClickListener(view -> {
            dismissAllowingStateLoss();
        });
    }

    private int getLayoutId() {
        return R.layout.layout_fullscreen_photo;
    }
}
