package com.spade.ja.ui.walkthrough;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IntroFragment extends BaseFragment {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;

    public static final String IMAGE = "image";
    public static final String TITLE = "title";
    public static final String SUBTITLE = "subtitle";

    public static IntroFragment newInstance(@DrawableRes int image, @StringRes int title ,@StringRes int subtitle) {
        Bundle args = new Bundle();
        args.putInt(IMAGE,image);
        args.putInt(TITLE,title);
        args.putInt(SUBTITLE,subtitle);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initializeDagger() {
        // no dependencies
    }

    @Override
    protected void initializePresenter() {
        // no presenter
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_intro_item;
    }

    @Override
    protected String getScreenTrackingName() {
        return "intro item";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            image.setImageDrawable(getResources().getDrawable(getArguments().getInt(IMAGE)));
            title.setText(getString(getArguments().getInt(TITLE)));
            subtitle.setText(getString(getArguments().getInt(SUBTITLE)));
        }
    }
}
