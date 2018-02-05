package com.spade.ja.ui.authentication.registeration;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.spade.ja.JaApplication;
import com.spade.ja.datalayer.pojo.response.login.Data;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.registeration.Registeration;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/20/2017.
 */

public class RegisterationPresenter extends BasePresenter<RegisterationContract.View> implements RegisterationContract.Presenter {

    private Registeration registeration;

    private BaseCallback<Data> callback = new BaseCallback<Data>() {
        @Override
        public void onSuccess(Data model) {
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
    RegisterationPresenter(Registeration registeration) {
        this.registeration = registeration;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    @Override
    public void register(String userName, String email, String password, String mobile, Uri image) {
        File imageFile = null;
        if (image != null) {
            imageFile = new File(getRealPathFromURI(image));
        }
        if (isViewAlive.get()){
            getView().showLoading();
        }
        registeration.register(userName, email, password, mobile, imageFile, callback);
    }

    @Override
    public void showSignIn() {
        jaNavigationManager.showSignInScreen();
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
    public void unSubscribe() {
        registeration.unSubscribe();
    }
}
