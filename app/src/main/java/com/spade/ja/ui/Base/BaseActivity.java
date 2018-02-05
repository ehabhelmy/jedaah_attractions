package com.spade.ja.ui.Base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.navigation.JaNavigationManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected BasePresenter presenter;

    private Unbinder unbinder;

    protected JaNavigationManager jaNavigationManager;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        JaNavigationManager.getInstance().setCurrentActivity(this);
        JaNavigationManager.getInstance().setFragmentManager(getSupportFragmentManager());
        initializeDagger();
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        unbinder.unbind();
    }

    protected void showPopUp(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle("Failure")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
