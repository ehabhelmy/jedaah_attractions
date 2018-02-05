package com.spade.ja.ui.Home.eventsinner.eventcheckout;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.EventTicket;
import com.spade.ja.datalayer.pojo.response.eventinner.TicketDate;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.EventDaysAdapter;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.EventTicketsAdapter;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.viewholder.TicketViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/27/17.
 */

public class EventOrderFragment extends BaseFragment implements EventOrderContract.View {

    @Inject
    EventOrderPresenter presenter;

    private int counter = 1;
    private int price;
    private String numberOfTickets;
    private int ticketId;
    private int dateId;
    private String eventId;
    private boolean national;
    private ArrayList<EventTicket> tickets;
    private EventTicketsAdapter eventTicketsAdapter;
    private ArrayList<TicketDate> days;
    private String payment;
    boolean isChoosed = true;

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

    @BindView(R.id.quantity)
    TextView quantity;

    @BindView(R.id.eventGrid)
    GridView daysGridView;

    @BindView(R.id.numTickets)
    TextView numTickets;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

    @BindView(R.id.ticketsList)
    RecyclerView ticketsList;

    @BindView(R.id.ticketsNumber)
    TextView ticketsNumberTextView;

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

    @OnClick(R.id.cancel)
    void cancelOrder(){
        getActivity().finish();
    }

    @OnClick(R.id.change)
    void changeOrder(){
        presenter.showPayment();
    }

    @OnClick(R.id.done)
    void order(){
        if (dateId == 0) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Order Failure")
                    .setMessage("you must select a date for the event")
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        }else {
            if (national) {
                presenter.order(nameTextView.getText().toString().trim(), emailTextView.getText().toString().trim(), mobileTextView.getText().toString().trim(), quantity.getText().toString().trim(), payment, eventId, ticketId + "", dateId + "", nationalIdTextView.getText().toString().trim(), totalPrice.getText().toString().replace("SAR", "").trim());
            } else {
                presenter.order(nameTextView.getText().toString().trim(), emailTextView.getText().toString().trim(), mobileTextView.getText().toString().trim(), quantity.getText().toString().trim(), payment, eventId, ticketId + "", dateId + "", null, totalPrice.getText().toString().replace("SAR", "").trim());
            }
        }
    }

    @OnClick(R.id.imagePlus)
    void incrementQuantity(){
        counter++;
        quantity.setText(counter+"");
        setPriceTotal();
    }

    @OnClick(R.id.imageMinus)
    void decrementQuantity(){
        if (!(counter == 1)) {
            counter--;
        }
        quantity.setText(counter+"");
        setPriceTotal();
    }

    private void setPriceTotal() {
        ticketsNumberTextView.setText(counter+" ticket");
        totalPrice.setText(counter*price+" SAR");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void setupOrderView(EventOrder eventOrder) {
        eventTitleTextView.setText(eventOrder.getEventTitle());
        nameTextView.setText(eventOrder.getName());
        emailTextView.setText(eventOrder.getEmail());
        mobileTextView.setText(eventOrder.getMobile());
        eventId = eventOrder.getEventId()+"";
        if (eventOrder.getNational() == null) {
            national = false;
            nationalIdTextView.setVisibility(View.GONE);
        }else {
            national = true;
            nationalIdTextView.setText(eventOrder.getNational());
        }
        payment = eventOrder.getPaymentMethod();
        switch (payment){
            case "cash_on_delivery" :
                paymentMethod.setText("Cash On Delivery");
                break;
            case "credit_card" :
                paymentMethod.setText("Credit Card");
                break;
            case "pay_later" :
                paymentMethod.setText("Pay Later");
                break;
        }
        numTickets.setText("Only "+eventOrder.getTickets()+" tickets are available");
        totalPrice.setText("");
        tickets = eventOrder.getEventtickets();
        eventTicketsAdapter = new EventTicketsAdapter();
        eventTicketsAdapter.setData(eventOrder.getEventtickets());
        eventTicketsAdapter.setTicketListener(new TicketListener() {
            @Override
            public void onTicketChecked(int position) {
                for (int i = 0 ; i < tickets.size() ; i++) {
                    if (i == position) {
                        price = Integer.parseInt(tickets.get(position).getPrice());
                        numTickets.setText("Only "+tickets.get(position).getNumberOfTickets()+" tickets are available");
                        ticketId = tickets.get(position).getId();
                        setPriceTotal();
                    }else {
                        TicketViewHolder ticketViewHolder = (TicketViewHolder) ticketsList.findViewHolderForAdapterPosition(i);
                        ticketViewHolder.uncheck();
                    }
                }
            }
        });
        ticketsList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.divider_vertical));
        ticketsList.addItemDecoration(dividerItemDecoration);
        ticketsList.setAdapter(eventTicketsAdapter);
        EventDaysAdapter adapter = new EventDaysAdapter(this.getContext());
        days = eventOrder.getTicketDates();
        adapter.setDays(eventOrder.getEventDateDays());
        daysGridView.setAdapter(adapter);
        daysGridView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0 ; i < daysGridView.getChildCount() ; i++) {
                if (i == position) {
                    if (isChoosed) {
                        view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_rectangle_black));
                        dateId = days.get(position).getId();
                        isChoosed = false;
                    }else {
                        view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_rectangle_grey));
                        dateId = 0;
                        isChoosed = true;
                    }
                }else {
                    View view1 = daysGridView.getChildAt(i);
                    view1.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.rounded_rectangle_grey));
                }
            }
        });
    }
}
