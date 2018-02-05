package com.spade.ja.ui.Home.directory.venues;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.directory.venues.adapter.AllVenuesListAdapter;
import com.spade.ja.ui.Home.directory.venues.adapter.ItemOffsetDecoration;
import com.spade.ja.ui.Home.explore.adapter.CategoryListAdapter;
import com.spade.ja.ui.Home.explore.adapter.VenuesListAdapter;

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
    RecyclerView categoriesRecyclarView;

    @BindView(R.id.topVeuesRecyclarView)
    RecyclerView topVeuesRecyclarView;

    @BindView(R.id.allVenuesRecyclarView)
    RecyclerView allVenuesRecyclarView;

    private ArrayList<com.spade.ja.datalayer.pojo.response.allvenues.Venue> response;
    private AllVenuesListAdapter allVenuesListAdapter;

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
            categoriesRecyclarView.setLayoutManager(layoutManager);
            categoriesRecyclarView.addItemDecoration(dividerItemDecoration);
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter();
            categoryListAdapter.setData((ArrayList<Cats>) categoryList);
            categoriesRecyclarView.setAdapter(categoryListAdapter);
        } else {
            categoriesRecyclarView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupTopVenues(List<Venue> venuesResponses) {
        if (venuesResponses.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.HORIZONTAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider));
            topVeuesRecyclarView.setLayoutManager(layoutManager);
            topVeuesRecyclarView.addItemDecoration(dividerItemDecoration);
            VenuesListAdapter venuesListAdapter = new VenuesListAdapter();
            venuesListAdapter.setData((ArrayList<Venue>) venuesResponses);
            venuesListAdapter.setOnFavouriteListener(id -> presenter.venueLike(id));
            venuesListAdapter.setOnViewListener(id -> presenter.showVenueInner(id));
            topVeuesRecyclarView.setAdapter(venuesListAdapter);
        } else {
            topVeuesRecyclarView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupAllVenues(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venuesResponses) {
        if (venuesResponses.size() > 0) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2);
            allVenuesRecyclarView.setLayoutManager(mLayoutManager);
            allVenuesRecyclarView.setItemAnimator(new DefaultItemAnimator());
            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this.getContext(), R.dimen.item_offset);
            allVenuesRecyclarView.addItemDecoration(itemDecoration);
            allVenuesListAdapter = new AllVenuesListAdapter();
            response = (ArrayList<com.spade.ja.datalayer.pojo.response.allvenues.Venue>) venuesResponses;
            allVenuesListAdapter.setData(response);
            allVenuesListAdapter.setOnFavouriteListener(id -> presenter.venueLike(id));
            allVenuesListAdapter.setOnViewListener(id -> presenter.showVenueInner(id));
            allVenuesRecyclarView.setAdapter(allVenuesListAdapter);
            allVenuesListAdapter.setOnLoadMoreListener(() -> presenter.loadMoreVenues());
            allVenuesListAdapter.setupLoadingRecyclerView(allVenuesRecyclarView);

        } else {
            allVenuesRecyclarView.setVisibility(View.GONE);
        }
    }

    @Override
    public void addVenues(List<com.spade.ja.datalayer.pojo.response.allvenues.Venue> venues) {
        if (venues != null) {
            allVenuesListAdapter.addData((ArrayList<com.spade.ja.datalayer.pojo.response.allvenues.Venue>) venues);
        }
    }

}
