package com.spade.ja.ui.Home.profile.edit;

import android.net.Uri;

import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.usecase.Unsubscribable;

/**
 * Created by ehab on 2/3/18.
 */

public interface EditContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void showProfileData(Data profileData);
    }

    interface Presenter extends Unsubscribable {
        void edit(String userName, String email,String dateOfBirth, String gender, String password, String mobile, Uri image);
    }
}
