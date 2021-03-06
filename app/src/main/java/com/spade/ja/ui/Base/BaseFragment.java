package com.spade.ja.ui.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.navigation.JaNavigationManager;

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

    protected abstract String getScreenTrackingName();

    @Override
    public void onResume() {
        super.onResume();
        trackScreen();
    }

    private void trackScreen() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplication();
        Tracker mTracker = jaApplication.getDefaultTracker();
        mTracker.setScreenName(getScreenTrackingName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showPopUp(String message) {
        new AlertDialog.Builder(this.getContext())
                .setMessage(message)
                .setTitle(getString(R.string.failure))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> dialog.dismiss())
                .show();
    }

    protected void showLoginRequiredError() {
        new AlertDialog.Builder(this.getActivity())
                .setMessage(getString(R.string.login_feature))
                .setTitle(getString(R.string.login_required))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                    jaNavigationManager.goToAuthActivity(null);
                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
