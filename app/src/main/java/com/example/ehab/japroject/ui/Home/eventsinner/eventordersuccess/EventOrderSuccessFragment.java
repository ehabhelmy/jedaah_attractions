package com.example.ehab.japroject.ui.Home.eventsinner.eventordersuccess;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 1/10/18.
 */

public class EventOrderSuccessFragment extends BaseFragment implements EventOrderSuccessContract.View {

    @Inject
    EventOrderSuccessPresenter presenter;

    @BindView(R.id.bookMore)
    TextView bookMore;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookMore.setPaintFlags(bookMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.bookMore)
    void bookMoreTickets() {
        presenter.bookMore();
    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_order_success;
    }
}