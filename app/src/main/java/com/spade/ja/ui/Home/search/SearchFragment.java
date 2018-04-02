package com.spade.ja.ui.Home.search;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.allnearby.Result;
import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.events.filterevents.adapter.FilterCategoriesAdapter;
import com.spade.ja.ui.Home.search.adapter.SearchListAdapter;
import com.spade.ja.ui.widget.StaticGridView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/20/18.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {

    @Inject
    SearchPresenter presenter;

    @BindView(R.id.searchEditText)
    AppCompatEditText searchInput;

    @BindView(R.id.searchResultsContainer)
    RelativeLayout resultsContainer;

    @BindView(R.id.filterByResults)
    TextView filterByText;

    @BindView(R.id.filterBtn)
    TextView filterBtn;

    @BindView(R.id.filterResult)
    RecyclerView filterResults;

    @BindView(R.id.searchContainer)
    RelativeLayout searchContainer;

    @BindView(R.id.weeklySugg)
    LinearLayout weeklySugg;

    @BindView(R.id.events)
    LinearLayout events;

    @BindView(R.id.venues)
    LinearLayout venues;

    @BindView(R.id.attractions)
    LinearLayout attractions;

    @BindView(R.id.eventsText)
    TextView eventsText;

    @BindView(R.id.venuesText)
    TextView venuesText;

    @BindView(R.id.attractionsText)
    TextView attractionsText;

    @BindView(R.id.tags)
    StaticGridView cats;

    private Set<Integer> categoriesChosen = new HashSet<>();
    private boolean isEventSelected = false;
    private boolean isVenueSelected = false;
    private boolean isAttractionSelected = false;

    @OnClick(R.id.events)
    void eventsSelected(){
        if (isEventSelected){
            events.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rect_light_grey));
            eventsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.lightBlack));
            isEventSelected = false;
        }else {
            eventsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
            events.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rectangle_black));
            isEventSelected = true;
        }
    }
    @OnClick(R.id.venues)
    void venuesSelected(){
        if (isVenueSelected){
            venues.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rect_light_grey));
            venuesText.setTextColor(ContextCompat.getColor(getActivity(),R.color.lightBlack));
            isVenueSelected = false;
        }else {
            venuesText.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
            venues.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rectangle_black));
            isVenueSelected = true;
        }
    }
    @OnClick(R.id.attractions)
    void attractionsSelected(){
        if (isAttractionSelected){
            attractionsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.lightBlack));
            attractions.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rect_light_grey));
            isAttractionSelected = false;
        }else {
            attractionsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
            attractions.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.rounded_rectangle_black));
            isAttractionSelected = true;
        }
    }
    @OnClick(R.id.searchEditText)
    void showFilters(){
        searchContainer.setVisibility(View.VISIBLE);
        resultsContainer.setVisibility(View.GONE);
    }
    @OnClick(R.id.search)
    void search() {
        List<String> types = new ArrayList<>();
        List<Integer> categories = new ArrayList<>();
        categories.addAll(categoriesChosen);
        if (isEventSelected){
            types.add("events");
        }
        if (isVenueSelected){
            types.add("venues");
        }
        if (isAttractionSelected){
            types.add("attractions");
        }
        presenter.search(searchInput.getText().toString().trim(),types,categories);
    }
    @Override
    public void showError(String message) {
        showPopUp(message);
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
        return R.layout.fragment_search;
    }

    @Override
    public void setupCategories(List<Cats> categories) {
        FilterCategoriesAdapter filterCategoriesAdapter = new FilterCategoriesAdapter(getActivity());
        filterCategoriesAdapter.setCats((ArrayList<Cats>) categories);
        cats.setAdapter(filterCategoriesAdapter);
        cats.setOnItemClickListener((adapterView, view, i, l) -> {
            Cats cats = (Cats) adapterView.getItemAtPosition(i);
            ImageView icon = view.findViewById(R.id.tagIcon);
            TextView text = view.findViewById(R.id.tagType);
            if (!categoriesChosen.contains(cats.getId())) {
                categoriesChosen.add(cats.getId());
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.tag_rect_green));
                icon.setColorFilter(ContextCompat.getColor(getActivity(),R.color.white));
                text.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
            } else {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.tag_rect));
                icon.setColorFilter(ContextCompat.getColor(getActivity(),R.color.colorTitle));
                text.setTextColor(ContextCompat.getColor(getActivity(),R.color.lightBlack));
                categoriesChosen.remove(cats.getId());
            }
        });
    }

    @Override
    public void showResults(List<Result> results) {
        searchContainer.setVisibility(View.GONE);
        resultsContainer.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_vertical));
        filterResults.setLayoutManager(layoutManager);
        filterResults.setItemAnimator(new DefaultItemAnimator());
        filterResults.addItemDecoration(dividerItemDecoration);
        SearchListAdapter dataAdapter = new SearchListAdapter();
        dataAdapter.setData((ArrayList<Result>) results);
        dataAdapter.setOnItemClick(new SearchListAdapter.onItemClick() {
            @Override
            public void onLikeClicked(int id, String type) {
                switch (type){
                    case "event":
                        presenter.like(id);
                        break;
                    case "venue":
                        presenter.venuesLike(id);
                        break;
                    case "attraction":
                        presenter.attractionsLike(id);
                        break;
                }
            }

            @Override
            public void onCardClicked(int id, String type) {
                switch (type){
                    case "event":
                        presenter.showEventInner(id);
                        break;
                    case "venue":
                        presenter.showVenuesInner(id);
                        break;
                    case "attraction":
                        presenter.showAttractionInner(id);
                        break;
                }
            }
        });
        filterResults.setAdapter(dataAdapter);
    }
}
