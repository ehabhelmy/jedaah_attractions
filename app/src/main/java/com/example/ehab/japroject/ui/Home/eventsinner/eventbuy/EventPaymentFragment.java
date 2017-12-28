package com.example.ehab.japroject.ui.Home.eventsinner.eventbuy;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTicket;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.TicketDate;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.eventbuy.pojo.PaymentData;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.OrderEventDay;
import com.example.ehab.japroject.ui.Home.explore.pojo.Event;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 12/25/17.
 */

public class EventPaymentFragment extends BaseFragment implements EventPaymentContract.View {

    @Inject
    EventPaymentPresenter presenter;

    @BindView(R.id.name)
    AppCompatEditText nameEditText;

    @BindView(R.id.email)
    AppCompatEditText emailEditText;

    @BindView(R.id.mobile)
    AppCompatEditText mobileEditText;

    @BindView(R.id.nationalIDLabel)
    TextView nationalLabel;

    @BindView(R.id.nationalID)
    AppCompatEditText nationalEditText;

    @BindView(R.id.creditImage)
    ImageView crediteImageView;

    @BindView(R.id.cashImage)
    ImageView cashImageView;

    @BindView(R.id.paylaterImage)
    ImageView payLaterImageView;

    @BindView(R.id.creditAmount)
    TextView creditAmountTextView;

    @BindView(R.id.cashAmount)
    TextView cashAmountTextView;

    @BindView(R.id.paylaterAmount)
    TextView payLaterTextView;

    @BindView(R.id.next)
    Button nextBtn;

    private String paymentType;

    private String eventTitle;

    private String tickets;

    private int creditTickets,cashTickets,payLaterTickets;

    private String vipPrice,regularPrice;

    private int eventId;

    private ArrayList<EventTicket> eventTickets;

    private ArrayList<TicketDate> ticketDates;

    private ArrayList<OrderEventDay> eventDateDays;

    private boolean nationalEnabled;

    private boolean credit,cash,paylater;

    private boolean creditToggle = true, cashToggle = true , payLaterToggle = true;

    @OnClick(R.id.creditImage)
    void creditSelected(){
        if (credit) {
            if (creditToggle) {
                crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_creditcard_deactivated));
                creditToggle = false;
            }else {
                crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_creditcard_active));
                creditToggle = true;
                tickets = "Unlimited";
                paymentType = "Credit Card";
            }
        }
    }

    @OnClick(R.id.cashImage)
    void cashSelected(){
        if (cash) {
            if (cashToggle) {
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_cod_deactivated));
                cashToggle = false;
            }else {
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_cod_active));
                cashToggle = true;
                tickets = cashTickets+"";
                paymentType = "Cash on delivery";
            }
        }
    }

    @OnClick(R.id.paylaterImage)
    void payLaterSelected(){
        if (paylater) {
            if (payLaterToggle) {
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_paylater_deactivated));
                payLaterToggle = false;
            }else {
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_paylater_active));
                payLaterToggle = true;
                tickets = payLaterTickets+"";
                paymentType = "Pay Later";
            }
        }
    }

    @OnClick(R.id.next)
    void orderTicket() {
        EventOrder eventOrder = new EventOrder();
        eventOrder.setEmail(emailEditText.getText().toString().trim());
        eventOrder.setName(nameEditText.getText().toString().trim());
        eventOrder.setMobile(mobileEditText.getText().toString().trim());
        eventOrder.setEventTitle(eventTitle);
        eventOrder.setPaymentMethod(paymentType);
        eventOrder.setVipPrice(vipPrice);
        eventOrder.setRegularPrice(regularPrice);
        eventOrder.setEventDateDays(eventDateDays);
        eventOrder.setEventId(eventId);
        eventOrder.setEventtickets(eventTickets);
        eventOrder.setTicketDates(ticketDates);
        if (nationalEnabled) {
            eventOrder.setNational(nationalEditText.getText().toString().trim());
        }
        eventOrder.setTickets(tickets);
        presenter.showOrderView(eventOrder);
    }

    private void hideNationalSection() {
        nationalEditText.setVisibility(View.GONE);
        nationalLabel.setVisibility(View.GONE);
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
        return R.layout.fragment_payment;
    }

    @Override
    public void setupPaymentView(PaymentData paymentData) {
        eventTitle = paymentData.getEventTitle();
        vipPrice = paymentData.getVipPrice();
        regularPrice = paymentData.getRegularPrice();
        eventDateDays = paymentData.getEventDateDays();
        cashTickets = paymentData.getCashTickets();
        eventTickets = paymentData.getTickets();
        eventId = paymentData.getEventId();
        ticketDates = paymentData.getTicketDates();
        payLaterTickets = paymentData.getPayLaterTickets();
        nationalEnabled = true;
        if (!paymentData.isNationalRequired()) {
            hideNationalSection();
            nationalEnabled = false;
        }
        if (!paymentData.isCreditCard()) {
            crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_creditcard_deactivated));
            creditAmountTextView.setText(0+"");
            credit = false;
        }else {
            credit = true;
            paymentType = "Credit Card";
        }
        if (!paymentData.isCash()) {
            cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_cod_deactivated));
            cashAmountTextView.setText(0+"");
            cash = false;
        }else {
            cashAmountTextView.setText(paymentData.getCashTickets()+"");
            cash = true;
            paymentType = "Cash on delivery";
        }
        if (!paymentData.isPayLater()) {
            payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.ic_paylater_deactivated));
            payLaterTextView.setText(0+"");
            paylater = false;
        }else {
            payLaterTextView.setText(paymentData.getPayLaterTickets()+"");
            paylater = true;
            paymentType = "Pay Later";
        }
    }
}
