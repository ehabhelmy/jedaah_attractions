package com.spade.ja.ui.Home.attractioninner;

import android.os.Bundle;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 3/9/18.
 */

public class AttractionInnerPresenter extends BasePresenter<AttractionInnerContract.View> implements AttractionInnerContract.Presenter {

    @Inject
    public AttractionInnerPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null) {
            jaNavigationManager.showAttractionDetails(extras.getInt(Constants.ATTRACTION_ID));
        }
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return jaNavigationManager.getCurrentFragmentOnInnerAttraction();
    }
}
