package com.example.ehab.japroject.ui.Home.directory;

import com.example.ehab.japroject.ui.Base.BasePresenter;

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
