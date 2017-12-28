package com.example.ehab.japroject.ui.Home.profile;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.profile.Data;
import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.widget.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    @Inject
    ProfilePresenter presenter;

    @BindView(R.id.profile_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.profile_viewpager)
    ViewPager profileViewPager;

    @BindView(R.id.edit)
    TextView editTextView;

    @BindView(R.id.username)
    TextView userName;

    @BindView(R.id.profile_image)
    CircularImageView profileImageView;

    @BindView(R.id.card_view)
    CardView profileCard;

    @BindView(R.id.id)
    TextView id;

    private ProfileViewPagerAdapter profileViewPagerAdapter;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);

    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        profileViewPagerAdapter = new ProfileViewPagerAdapter(getActivity().getSupportFragmentManager());
        profileViewPager.setOffscreenPageLimit(1);
        profileViewPager.setAdapter(profileViewPagerAdapter);
        tabLayout.setupWithViewPager(profileViewPager);
    }

    @OnClick(R.id.share)
    void saveProfilePhoto(){
        saveUserCard(getBitmapFromView(profileCard));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void updateProfileFragment(Data model) {
        userName.setText(model.getName());
        id.setText("JA ID : "+model.getId());
        Picasso.with(this.getContext()).load(model.getProfileImage()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(profileImageView);
    }

    public Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public void saveUserCard(Bitmap bitmap){
        File directory = new File(Environment.getExternalStorageDirectory()
                + File.separator);
        File file = new File(directory, "PROFILE_CARD");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
