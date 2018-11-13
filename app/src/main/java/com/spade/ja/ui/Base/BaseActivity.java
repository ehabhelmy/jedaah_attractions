package com.spade.ja.ui.Base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.navigation.JaNavigationManager;
import com.spade.ja.util.LocaleManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.pm.PackageManager.GET_META_DATA;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected BasePresenter presenter;

    private Unbinder unbinder;

    protected JaNavigationManager jaNavigationManager;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        JaNavigationManager.getInstance().setCurrentActivity(this);
        JaNavigationManager.getInstance().setFragmentManager(getSupportFragmentManager());
        unbinder = ButterKnife.bind(this);
        initializeDagger();
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
        resetTitles();
    }

    private void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        JaNavigationManager.getInstance().setCurrentActivity(this);
        JaNavigationManager.getInstance().setFragmentManager(getSupportFragmentManager());
        if (presenter != null){
            presenter.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null){
            presenter.finalizeView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showPopUp(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle("Failure")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
