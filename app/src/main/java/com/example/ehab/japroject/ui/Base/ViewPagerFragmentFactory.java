package com.example.ehab.japroject.ui.Base;


/**
 * Created by ehab on 12/6/17.
 */

public class ViewPagerFragmentFactory {

    public enum ViewPagerFragment {
        HOME,LOGIN
    }

    public static BaseFragment getFragmentByName(ViewPagerFragment viewPagerFragment){
        switch (viewPagerFragment){
            case HOME:
                // TODO : return dedicated fragment
        }
        return null;
    }
}
