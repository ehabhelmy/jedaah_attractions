package com.spade.ja.ui.walkthrough;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.spade.ja.R;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.ui.Base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkThroughActivity extends BaseActivity implements WalkThroughContract.View {

    @Inject
    WalkThroughPresenter presenter;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabDots)
    TabLayout tabDots;

    private ViewPagerAdapter adapter;

    @Override
    protected void initializeDagger() {
        DaggerMainComponent.create().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_walkthrough;
    }

    @Override
    protected String getScreenTrackingName() {
        return "Intro Screen";
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        tabDots.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(adapter);
    }

    @OnClick({R.id.next, R.id.skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (viewPager.getCurrentItem() < adapter.getCount() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    presenter.skip();
                }
                break;
            case R.id.skip:
                presenter.skip();
                break;
        }
    }
}
