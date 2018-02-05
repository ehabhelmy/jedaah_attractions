package com.spade.ja.ui.Home.directory.attractions;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;

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
