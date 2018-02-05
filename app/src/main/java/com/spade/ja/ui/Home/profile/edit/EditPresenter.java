package com.spade.ja.ui.Home.profile.edit;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.edit.Edit;
import com.spade.ja.util.Constants;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by ehab on 2/3/18.
 */

public class EditPresenter extends BasePresenter<EditContract.View> implements EditContract.Presenter {

    private Edit edit;

    private BaseCallback<LoginResponse> callback = new BaseCallback<LoginResponse>() {
        @Override
        public void onSuccess(LoginResponse model) {
            if (isViewAlive.get()){
                getView().hideLoading();
            }
            jaNavigationManager.goToHomeActivity();
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };

    @Inject
    public EditPresenter(Edit edit) {
        this.edit = edit;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            Data profileData = extras.getParcelable(Constants.PROFILE_DATA);
            getView().showProfileData(profileData);
        }
    }

    @Override
    public void unSubscribe() {
        edit.unSubscribe();
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(JaApplication.getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void edit(String userName, String email, String dateOfBirth, String gender, String password, String mobile, Uri image) {
        File imageFile = null;
        if (image != null) {
            imageFile = new File(getRealPathFromURI(image));
        }
        if (isViewAlive.get()){
            getView().showLoading();
        }
        edit.edit(userName,email,dateOfBirth,gender,password,mobile,imageFile,callback);
    }
}
