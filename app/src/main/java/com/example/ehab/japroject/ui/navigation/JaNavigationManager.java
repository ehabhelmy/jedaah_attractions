package com.example.ehab.japroject.ui.navigation;

import android.content.Context;
import android.os.Bundle;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.ui.Base.BaseActivity;
import com.example.ehab.japroject.ui.Base.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class JaNavigationManager {

    protected static Context context;

    protected static JaNavigationManager jaNavigationManager;

    protected WeakReference<BaseActivity> currentActivity;


    public static JaNavigationManager getInstance(){
        if (jaNavigationManager != null){
            context = JaApplication.getContext();
            // TODO : check if it is tablet then return instance of tablet navigation
            jaNavigationManager = new JaPortraitNavigationManager();
        }
        return jaNavigationManager;
    }

    private void addFragment(BaseFragment fragment,boolean addToBackStack){

    }

    private void replaceFragment(BaseFragment fragment,boolean addToBackStack){

    }

    public abstract void navigateToLogin();

    public abstract void navigateToHome(User user);

    public abstract void navigateToDetails(Bundle bundle);


    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<BaseActivity>(currentActivity);
    }
}
