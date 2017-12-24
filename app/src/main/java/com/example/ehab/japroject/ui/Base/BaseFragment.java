package com.example.ehab.japroject.ui.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.Base.listener.ErrorView;
import com.example.ehab.japroject.ui.navigation.JaNavigationManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    protected FragmentManager fragmentManager;

    protected BasePresenter presenter;

    protected JaNavigationManager jaNavigationManager;

    private View view;

    private Unbinder unbinder;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    protected abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        jaNavigationManager = JaNavigationManager.getInstance();
        jaNavigationManager.setCurrentActivity((BaseActivity) getActivity());
        initializeDagger();
        initializePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        if (presenter != null){
            presenter.initialize(getArguments());
        }
        return view;
    }

    public void locationIsEnabled(){

    }

    public void onErrorAuth(String message) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null){
            presenter.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null){
            presenter.finalizeView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void showPopUp(String message) {
        new AlertDialog.Builder(this.getContext())
                .setMessage(message)
                .setTitle("Login Failure")
                .show();
    }
}
