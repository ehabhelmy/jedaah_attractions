package com.example.ehab.japroject.ui.Base;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.listener.BaseView;
import com.example.ehab.japroject.ui.navigation.JaNavigationManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ehab on 12/1/17.
 */

public abstract class BasePresenter<T extends BaseView> {


    private WeakReference<T> view;

    protected JaNavigationManager jaNavigationManager = JaNavigationManager.getInstance();

    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public T getView() {
        return view.get();
    }

    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public void initialize(Bundle extras) {
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

}
