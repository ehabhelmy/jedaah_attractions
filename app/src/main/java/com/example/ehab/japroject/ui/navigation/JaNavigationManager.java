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

    public static final String EVENTS = "events";

    public static final String SOCIAL = "social";

    public static final int LOCATION_SETTINGS = 2;


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

    public abstract void showEventsScreen();

    public abstract void showLocationSettings();

    public abstract void showSocialMediaScreen();

    protected void addFragment(BaseFragment fragment,boolean addToBackStack){

    }

    protected void replaceFragment(BaseFragment fragment,boolean addToBackStack,String tag,int frame){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(frame,fragment,tag);
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public  <F extends BaseFragment> F getCurrentFragment(){
        return (F) fragmentManager.findFragmentById(R.id.frame_layout);
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<BaseActivity>(currentActivity);
    }
}
