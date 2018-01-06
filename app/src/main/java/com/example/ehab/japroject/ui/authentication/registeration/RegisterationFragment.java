package com.example.ehab.japroject.ui.authentication.registeration;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.authentication.AuthenticationContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Romisaa on 12/20/2017.
 */

public class RegisterationFragment extends BaseFragment implements RegisterationContract.View {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE};

    private AuthenticationContract.View activity;

    @BindView(R.id.username)
    AppCompatEditText userName;

    @BindView(R.id.email)
    AppCompatEditText email;

    @BindView(R.id.password)
    AppCompatEditText password;

    @BindView(R.id.number)
    AppCompatEditText mobileNumber;

    @BindView(R.id.add_image)
    ImageView addImage;

    @BindView(R.id.profile_image)
    ImageView profileImage;

    @BindView(R.id.registerNow)
    Button register;

    @BindView(R.id.registerContainer)
    RelativeLayout registerContainer;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @Inject
    RegisterationPresenter presenter;

    private Uri uri;

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
    }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addImage.setOnClickListener(view1 -> {
            verifyStoragePermissions(getActivity());
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        registerContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        registerContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        if (message !=  null) {
            showPopUp(message);
        }else {
            showPopUp("Server Error");
        }
    }

    @OnClick(R.id.registerNow)
    void register() {
        hideKeyboard();
        if (TextUtils.isEmpty(userName.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText())) {
            new AlertDialog.Builder(this.getActivity())
                    .setTitle("Data Required")
                    .setMessage("You need to fill all the data")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        }else {
            this.presenter.register(userName.getText().toString()
                    , email.getText().toString()
                    , password.getText().toString()
                    , mobileNumber.getText().toString(), uri);
        }
    }

    @OnClick(R.id.signIn)
    void showSignIn(){
        hideKeyboard();
        presenter.showSignIn();
    }

    @OnClick(R.id.skip_tv)
    void skipAuth(){
        activity.skipAuth();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AuthenticationContract.View) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            uri = data.getData();
            profileImage.setImageURI(data.getData());
        }
    }

    private void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }else{
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }else{
                    //TODO : show error and disable functionality
                    showErrorYouCantUploadImage();
                }
        }
    }

    private void showErrorYouCantUploadImage() {
        new AlertDialog.Builder(getContext())
                .setMessage("You can't update your profile image")
                .show();
    }
}



