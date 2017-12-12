package com.example.ehab.japroject.ui.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseActivity;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;

import java.lang.ref.WeakReference;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class JaNavigationManager {

    protected static Context context;

    protected FragmentManager fragmentManager;

    protected static JaNavigationManager jaNavigationManager;

    protected WeakReference<BaseActivity> currentActivity;

    public static final String EXPLORE = "explore";


    public static JaNavigationManager getInstance(){
        if (jaNavigationManager == null){
            context = JaApplication.getContext();
            // TODO : check if it is tablet then return instance of tablet navigation
            jaNavigationManager = new JaPortraitNavigationManager();
        }
        return jaNavigationManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public abstract void showExploreScreen();

    protected void addFragment(BaseFragment fragment,boolean addToBackStack){

    }

    protected void replaceFragment(BaseFragment fragment,boolean addToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment,EXPLORE);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<BaseActivity>(currentActivity);
    }
}
