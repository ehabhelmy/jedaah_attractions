package com.spade.ja.ui.Home.profile;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.widget.CircularImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

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
        profileViewPager.setAdapter(profileViewPagerAdapter);
        tabLayout.setupWithViewPager(profileViewPager);
    }

    @OnClick(R.id.edit)
    void editProfile(){
        presenter.editProfile();
    }

    @OnClick(R.id.share)
    void saveProfilePhoto(){
        new AlertDialog.Builder(this.getContext())
                .setTitle(getString(R.string.take_screen_shot))
                .setMessage(getString(R.string.id_card))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> saveUserCard(getBitmapFromView(profileCard)))
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
    }
    @OnClick(R.id.settings)
    void openSettings(){
        presenter.openSettings();
    }

    private void logout() {
        new AlertDialog.Builder(this.getContext())
                .setTitle(getString(R.string.logout))
                .setMessage(getString(R.string.logout_confirm))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> presenter.logOut())
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
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
        if (!TextUtils.isEmpty(model.getProfileImage())) {
            //Glide.with(this.getContext()).load(model.getProfileImage()).(R.drawable.ic_profile_default).error(R.drawable.ic_profile_default).into(profileImageView);
            Glide.with(this).load(model.getProfileImage()).apply(new RequestOptions().placeholder(R.drawable.profile_default).error(R.drawable.profile_default)).into(profileImageView);
        }
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

    public String insertImage(ContentResolver cr,
                                           Bitmap source,
                                           String title,
                                           String description) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;
        String stringUrl = null;    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

                long id = ContentUris.parseId(url);
                // Wait until MINI_KIND thumbnail is generated.
                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                // This is for backward compatibility.
                storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
            } else {
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }

    private static final Bitmap storeThumbnail(
            ContentResolver cr,
            Bitmap source,
            long id,
            float width,
            float height,
            int kind) {

        // create the matrix to scale it
        Matrix matrix = new Matrix();

        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();

        matrix.setScale(scaleX, scaleY);

        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(),
                source.getHeight(), matrix,
                true
        );

        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Thumbnails.KIND,kind);
        values.put(MediaStore.Images.Thumbnails.IMAGE_ID,(int)id);
        values.put(MediaStore.Images.Thumbnails.HEIGHT,thumb.getHeight());
        values.put(MediaStore.Images.Thumbnails.WIDTH,thumb.getWidth());

        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream thumbOut = cr.openOutputStream(url);
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
            thumbOut.close();
            return thumb;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }


    public void saveUserCard(Bitmap bitmap){
        insertImage(getActivity().getContentResolver(),bitmap,"Profile-Card","Jeddah Attractions");
    }
}
