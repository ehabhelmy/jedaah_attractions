package com.example.ehab.japroject.ui.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.ui.Home.HomeActivity;
import com.example.ehab.japroject.util.Constants;

/**
 * Created by ehab on 12/1/17.
 */

public class JaPortraitNavigationManager extends JaNavigationManager {

    @Override
    public void navigateToLogin() {

    }

    @Override
    public void navigateToHome(User user) {
        if (getCurrentActivity() != null){
        Intent intent = new Intent(getCurrentActivity(), HomeActivity.class);
        if (user != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.USER,user);
            intent.putExtras(bundle);
        }
        getCurrentActivity().startActivity(intent);}

    }

    @Override
    public void navigateToDetails(Bundle bundle) {

    }
}
