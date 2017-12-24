package com.example.ehab.japroject.ui.authentication.registeration;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.Data;
import com.example.ehab.japroject.datalayer.pojo.request.registeration.RegisterationResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.registeration.Registeration;

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
            //TODO GO TO AUTHENTICATION ACTIVITY
            jaNavigationManager.showSignInScreen();
            Log.i("Registeration Presenter", "Success");
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
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

}
