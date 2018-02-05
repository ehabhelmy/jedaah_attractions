package com.spade.ja.ui.Home.directory;

import com.spade.ja.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Roma on 1/14/2018.
 */

public class DirectoryPresenter extends BasePresenter<DirectoryContract.View> implements DirectoryContract.Presenter{

    @Inject
    public DirectoryPresenter() {
    }

    @Override
    public void unSubscribe() {

    }
}
