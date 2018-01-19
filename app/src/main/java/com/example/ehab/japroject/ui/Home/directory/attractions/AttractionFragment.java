package com.example.ehab.japroject.ui.Home.directory.attractions;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;

/**
 * Created by Roma on 1/14/2018.
 */

public class AttractionFragment extends BaseFragment implements AttractionsContract.View{
    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attractions;
    }
}
