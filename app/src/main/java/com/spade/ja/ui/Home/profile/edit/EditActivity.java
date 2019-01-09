package com.spade.ja.ui.Home.profile.edit;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 2/3/18.
 */

public class EditActivity extends BaseActivity implements EditContract.View {

    @Inject
    EditPresenter presenter;

    @BindView(R.id.name)
    AppCompatEditText userName;

    @BindView(R.id.newPassword)
    AppCompatEditText newPassword;

    @BindView(R.id.confirmPassword)
    AppCompatEditText confirmPassword;

    @BindView(R.id.email)
    AppCompatEditText email;

    @BindView(R.id.birthday)
    AppCompatEditText dob;

    @BindView(R.id.male)
    CheckBox male;

    @BindView(R.id.female)
    CheckBox female;

    @BindView(R.id.mobile)
    AppCompatEditText mobileNumber;

    @BindView(R.id.add_image)
    FrameLayout addImage;

    @BindView(R.id.profile_image)
    ImageView profileImage;

    @BindView(R.id.update)
    Button update;

    @BindView(R.id.registerContainer)
    RelativeLayout registerContainer;

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE};

    private Uri uri;

    Calendar calendar = Calendar.getInstance();


    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addImage.setOnClickListener(view1 -> verifyStoragePermissions(this));
        male.setOnCheckedChangeListener((compoundButton, b) -> {
            if (male.isChecked()) {
                female.setChecked(false);
            }
        });
        female.setOnCheckedChangeListener((compoundButton, b) -> {
            if (female.isChecked()){
                male.setChecked(false);
            }
        });
    }

    @Override
    protected String getScreenTrackingName() {
        return "edit profile";
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
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.profile_image))
                .show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
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

    @OnClick(R.id.birthday)
    void openDatePicker() {
        new DatePickerDialog(this, (datePicker, i, i1, i2) -> {
            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel();
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        dob.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void showError(String message) {
        if (message !=  null) {
            showPopUp(message);
        }else {
            showPopUp("Server Error");
        }
    }

    @OnClick(R.id.update)
    void updateProfile() {
        hideKeyboard();
        if (!newPassword.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.failure))
                    .setMessage(getString(R.string.password_confirmation))
                    .setPositiveButton(getString(R.string.ok),(dialogInterface, i) -> {
                        newPassword.setText("");
                        confirmPassword.setText("");
                        dialogInterface.dismiss();
                    }).show();
        }else {
            if (female.isChecked()) {
                presenter.edit(userName.getText().toString().trim(), email.getText().toString().trim(), dob.getText()
                        .toString().trim(), "female", newPassword.getText().toString().trim(), mobileNumber.getText().toString().trim(), uri);
            } else {
                presenter.edit(userName.getText().toString().trim(), email.getText().toString().trim(), dob.getText()
                        .toString().trim(), "male", newPassword.getText().toString().trim(), mobileNumber.getText().toString().trim(), uri);
            }
        }
    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    public void showProfileData(Data profileData) {
        if (profileData != null) {
            if (!TextUtils.isEmpty(profileData.getProfileImage())) {
                //Glide.with(this.getContext()).load(model.getProfileImage()).(R.drawable.ic_profile_default).error(R.drawable.ic_profile_default).into(profileImageView);
                Glide.with(this).load(profileData.getProfileImage()).apply(new RequestOptions().placeholder(R.drawable.profile_default).error(R.drawable.profile_default)).into(profileImage);
            }
            userName.setText(profileData.getName());
            email.setText(profileData.getEmail());
            mobileNumber.setText(profileData.getMobileNumber());
            dob.setText(profileData.getBirthDate());
            if (profileData.getGender() != null) {
                if (profileData.getGender().equalsIgnoreCase("female")) {
                    female.setChecked(true);
                } else {
                    male.setChecked(true);
                }
            }
        }
    }
}
