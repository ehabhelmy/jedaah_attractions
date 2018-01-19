package com.example.ehab.japroject.ui.Home.directory.venues;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.category.Cats;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.explore.adapter.CategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Roma on 1/14/2018.
 */

public class VenuesFragment extends BaseFragment implements VenuesContract.View {

    @Inject
    VenuesPresenter presenter;

    @BindView(R.id.categories)
    RecyclerView categories;

    @BindView(R.id.topSportsRecyclarView)
    RecyclerView topSportsRecyclarView;

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        return R.layout.fragment_venues;
    }

    @Override
    public void setupCategories(List<Cats> categoryList) {
        if (categoryList.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.category_divider));
            categories.setLayoutManager(layoutManager);
            categories.addItemDecoration(dividerItemDecoration);
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter();
            categoryListAdapter.setData((ArrayList<Cats>) categoryList);
            categories.setAdapter(categoryListAdapter);
        }else {
            categories.setVisibility(View.GONE);
        }
    }

}
