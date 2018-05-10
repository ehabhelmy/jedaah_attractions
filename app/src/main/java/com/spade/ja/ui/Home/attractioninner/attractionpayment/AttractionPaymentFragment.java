package com.spade.ja.ui.Home.attractioninner.attractionpayment;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.attractioninner.AttractionOrder.pojo.AttractionOrder;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/14/18.
 */

public class AttractionPaymentFragment extends BaseFragment implements AttractionPaymentContract.View {

    @Inject
    AttractionPaymentPresenter presenter;

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

    @BindView(R.id.creditContainer)
    LinearLayout creditContainer;

    @BindView(R.id.cashContainer)
    LinearLayout cashContainer;

    @BindView(R.id.payLaterContainer)
    LinearLayout payLaterContainer;

    private String paymentType;

    private String attractionTitle;

    private String tickets;

    private int creditTickets,cashTickets,payLaterTickets;

    private int attractionId;

    private boolean nationalEnabled;

    private boolean credit,cash,paylater;

    private TimeModel timeModel;

    private boolean creditToggle = false, cashToggle = false , payLaterToggle = false;

    @OnClick(R.id.creditImage)
    void creditSelected(){
        if (credit) {
            if (creditToggle) {
                crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.creditcard_deactivated));
                creditToggle = false;
                paymentType = null;
            }else {
                crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.creditcard_active));
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.cod_deactivated));
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.paylater_deactivated));
                creditToggle = true;
                tickets = "Unlimited";
                paymentType = "credit_card";
            }
        }
    }

    @OnClick(R.id.cashImage)
    void cashSelected(){
        if (cash) {
            if (cashToggle) {
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.cod_deactivated));
                cashToggle = false;
                paymentType = null;
            }else {
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.cod_active));
                crediteImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.creditcard_deactivated));
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.paylater_deactivated));
                cashToggle = true;
                tickets = cashTickets+"";
                paymentType = "cash_on_delivery";
            }
        }
    }

    @OnClick(R.id.paylaterImage)
    void payLaterSelected(){
        if (paylater) {
            if (payLaterToggle) {
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.paylater_deactivated));
                payLaterToggle = false;
                paymentType = null;
            }else {
                payLaterImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.paylater_active));
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.cod_deactivated));
                cashImageView.setImageDrawable(ContextCompat.getDrawable(this.getContext(),R.drawable.cod_deactivated));
                payLaterToggle = true;
                tickets = payLaterTickets+"";
                paymentType = "pay_later";
            }
        }
    }

    @OnClick(R.id.next)
    void orderTicket() {
        if (TextUtils.isEmpty(emailEditText.getText())
                || TextUtils.isEmpty(nameEditText.getText())
                || TextUtils.isEmpty(mobileEditText.getText())
                || (TextUtils.isEmpty(nationalEditText.getText()) && nationalEditText.getVisibility() == View.VISIBLE)
                || TextUtils.isEmpty(paymentType)){

            new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.failure))
                    .setMessage(getString(R.string.fill_data))
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();

        }else {
            AttractionOrder attractionOrder = new AttractionOrder();
            attractionOrder.setEmail(emailEditText.getText().toString().trim());
            attractionOrder.setName(nameEditText.getText().toString().trim());
            attractionOrder.setMobile(mobileEditText.getText().toString().trim());
            attractionOrder.setAttractionTitle(attractionTitle);
            attractionOrder.setPaymentMethod(paymentType);
            attractionOrder.setAttractionId(attractionId);
            attractionOrder.setTimeModel(timeModel);
            if (nationalEnabled) {
                attractionOrder.setNational(nationalEditText.getText().toString().trim());
            }
            presenter.showOrderView(attractionOrder);
        }
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
    public void setupPaymentView(AttractionPaymentModel paymentData) {
        attractionTitle = paymentData.getAttractionTitle();
        cashTickets = paymentData.getCashTickets();
        attractionId = paymentData.getAttractionId();
        cashTickets = paymentData.getCashTickets();
        payLaterTickets = paymentData.getPayLaterTickets();
        timeModel = paymentData.getTime();
        payLaterTickets = paymentData.getPayLaterTickets();
        nationalEnabled = true;
        if (!paymentData.isNationalRequired()) {
            hideNationalSection();
            nationalEnabled = false;
        }
        if (!paymentData.isCreditCard()) {
            creditContainer.setVisibility(View.GONE);
        }else {
            credit = true;
        }
        if (!paymentData.isCash()) {
            cashContainer.setVisibility(View.GONE);
        }else {
            cashAmountTextView.setText(paymentData.getCashTickets()+"");
            cash = true;
        }
        if (!paymentData.isPayLater()) {
            payLaterContainer.setVisibility(View.GONE);
        }else {
            payLaterTextView.setText(paymentData.getPayLaterTickets()+"");
            paylater = true;
        }
    }

    @Override
    public void setupUserData(User user) {
        nameEditText.setText(user.getName());
        emailEditText.setText(user.getEmail());
        mobileEditText.setText(user.getMobileNumber());
    }
}
