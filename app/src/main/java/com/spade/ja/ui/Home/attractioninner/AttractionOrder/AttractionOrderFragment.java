package com.spade.ja.ui.Home.attractioninner.AttractionOrder;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.request.attractionorder.Ticket;
import com.spade.ja.datalayer.pojo.response.viewtickets.Addon;
import com.spade.ja.datalayer.pojo.response.viewtickets.Type;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.adapter.AddOnsAdapter;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.adapter.TicketAdapter;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.viewholder.AddOnViewHolder;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.viewholder.TicketViewHolder;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;
import com.spade.ja.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionOrderFragment extends BaseFragment implements AttractionOrderContract.View {

    @Inject
    AttractionOrderPresenter presenter;

    private String numberOfTickets;
    private int attractionId;
    private boolean national;
    private String payment;
    private String date_id;
    private TimeModel timeModel;
    private TicketAdapter ticketAdapter;
    private AddOnsAdapter addOnsAdapter;

    @BindView(R.id.eventTitle)
    TextView eventTitleTextView;

    @BindView(R.id.name)
    TextView nameTextView;

    @BindView(R.id.email)
    TextView emailTextView;

    @BindView(R.id.mobile)
    TextView mobileTextView;

    @BindView(R.id.nationalId)
    TextView nationalIdTextView;

    @BindView(R.id.paymentMethod)
    TextView paymentMethod;

    @BindView(R.id.attractionMonth)
    TextView attractionMonth;

    @BindView(R.id.attractionDay)
    TextView attractionDay;

    @BindView(R.id.attractionTime)
    TextView attractionTime;

    @BindView(R.id.changeTime)
    Button changeTime;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

    @BindView(R.id.ticketsList)
    RecyclerView ticketsList;

    @BindView(R.id.addOnsList)
    RecyclerView addOnsList;

    @BindView(R.id.ticketsNumber)
    TextView ticketsNumberTextView;

    @BindView(R.id.load)
    LinearLayout loadingView;

    @BindView(R.id.nestedScroll)
    NestedScrollView scrollView;

    private boolean isTimeChosen = false;
    private int totPrice = 0;
    private int numTickets = 0;

    @OnClick(R.id.changeTime)
    void showCalendar() {
        presenter.showCalendar();
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        scrollView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
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

    @OnClick(R.id.cancel)
    void cancelOrder() {
        getActivity().finish();
    }

    @OnClick(R.id.change)
    void changeOrder() {
        presenter.showPayment();
    }

    private List<Ticket> getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0 ; i < ticketAdapter.getItemCount() ; i++){
            TicketViewHolder ticketViewHolder = (TicketViewHolder) ticketsList.getChildViewHolder(ticketsList.getChildAt(i));
            Ticket ticket = new Ticket(ticketViewHolder.getId(),null,ticketViewHolder.getTicketNumber());
            tickets.add(ticket);
        }
        for (int i = 0 ; i < addOnsAdapter.getItemCount() ; i++){
            AddOnViewHolder addOnViewHolder = (AddOnViewHolder) addOnsList.getChildViewHolder(addOnsList.getChildAt(i));
            Ticket ticket = new Ticket(null,addOnViewHolder.getId(),addOnViewHolder.getTicketNumber());
            tickets.add(ticket);
        }
        return tickets;
    }

    @OnClick(R.id.done)
    void order() {
        if (national) {

        } else {
            List<Ticket> tickets = getAllTickets();
            if (timeModel.getType() == null) {
                presenter.order(nameTextView.getText().toString().trim(), emailTextView.getText().toString().trim(), mobileTextView.getText().toString().trim(), payment, attractionId, totPrice, null, timeModel.getId(), tickets);
            }else {
                presenter.order(nameTextView.getText().toString().trim(), emailTextView.getText().toString().trim(), mobileTextView.getText().toString().trim(), payment, attractionId, totPrice, timeModel.getId(), null, tickets);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attraction_order;
    }

    @Override
    public void setupOrderView(AttractionOrder attractionOrder) {
        eventTitleTextView.setText(attractionOrder.getAttractionTitle());
        nameTextView.setText(attractionOrder.getName());
        emailTextView.setText(attractionOrder.getEmail());
        mobileTextView.setText(attractionOrder.getMobile());
        attractionId = attractionOrder.getAttractionId();
        date_id = attractionOrder.getTimeModel().getId() + "";
        timeModel = attractionOrder.getTimeModel();
        if (attractionOrder.getNational() == null) {
            national = false;
            nationalIdTextView.setVisibility(View.GONE);
        } else {
            national = true;
            nationalIdTextView.setText(attractionOrder.getNational());
        }
        payment = attractionOrder.getPaymentMethod();
        switch (payment) {
            case "cash_on_delivery":
                paymentMethod.setText("Cash On Delivery");
                break;
            case "credit_card":
                paymentMethod.setText("Credit Card");
                break;
            case "pay_later":
                paymentMethod.setText("Pay Later");
                break;
        }
        totalPrice.setText("");
        attractionMonth.setText(DateTimeUtils.getMonth(DateTimeUtils.convertJSONDateToDate(attractionOrder.getTimeModel().getDate())));
        attractionDay.setText(DateTimeUtils.getDay(DateTimeUtils.convertJSONDateToDate(attractionOrder.getTimeModel().getDate())));
        attractionTime.setText(DateTimeUtils.getWeekDay() + " " + attractionOrder.getTimeModel().getStartTime());
    }

    @Override
    public void setupTicketTypes(List<Type> tickets) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        ticketsList.setLayoutManager(layoutManager);
        ticketsList.addItemDecoration(dividerItemDecoration);
        ticketsList.setItemAnimator(new DefaultItemAnimator());
        ticketAdapter = new TicketAdapter();
        ticketAdapter.setData((ArrayList<Type>) tickets);
        ticketAdapter.setOnFavouriteListener(id -> {
            totPrice = totPrice + id;
            totalPrice.setText(totPrice + " SAR");
        });
        ticketAdapter.setOnViewListener(id -> {
            numTickets = numTickets + id;
            ticketsNumberTextView.setText(numTickets + " ticket");
        });
        ticketsList.setAdapter(ticketAdapter);
    }

    @Override
    public void setupAddOns(List<Addon> addons) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        addOnsList.setLayoutManager(layoutManager);
        addOnsList.addItemDecoration(dividerItemDecoration);
        addOnsList.setItemAnimator(new DefaultItemAnimator());
        addOnsAdapter = new AddOnsAdapter();
        addOnsAdapter.setData((ArrayList<Addon>) addons);
        addOnsAdapter.setOnFavouriteListener(id -> {
            totPrice = totPrice + id;
            totalPrice.setText(totPrice + " SAR");
        });
        addOnsAdapter.setOnViewListener(id -> {
            numTickets = numTickets + id;
            ticketsNumberTextView.setText(numTickets + " ticket");
        });
        addOnsList.setAdapter(addOnsAdapter);
    }
}
