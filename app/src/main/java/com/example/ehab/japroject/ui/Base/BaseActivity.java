package com.example.ehab.japroject.ui.Base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.BaseModel;
import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.navigation.JaNavigationManager;

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
        jaNavigationManager = JaNavigationManager.getInstance();
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
}
