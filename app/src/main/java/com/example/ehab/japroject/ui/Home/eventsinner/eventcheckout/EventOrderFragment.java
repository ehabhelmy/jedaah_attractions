package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseFragment;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.EventDaysAdapter;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.pojo.EventOrder;

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
    private int vipprice,regularprice;

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

    @BindView(R.id.vipCheckbox)
    CheckBox vipCheckBox;

    @BindView(R.id.regularCheckBox)
    CheckBox regularCheckBox;

    @BindView(R.id.vipPrice)
    TextView vipPrice;

    @BindView(R.id.regularPrice)
    TextView regularPrice;

    @BindView(R.id.numTickets)
    TextView numTickets;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

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

    }

    @OnClick(R.id.imagePlus)
    void incrementQuantity(){
        counter++;
        quantity.setText(counter+"");
        if (vipCheckBox.isChecked()) {
            totalPrice.setText(counter*vipprice+" SAR");
        }
        if (regularCheckBox.isChecked()){
            totalPrice.setText(counter*regularprice+" SAR");
        }
    }

    @OnClick(R.id.imageMinus)
    void decrementQuantity(){
        counter--;
        quantity.setText(counter+"");
        if (vipCheckBox.isChecked()) {
            totalPrice.setText(counter*vipprice+" SAR");
        }
        if (regularCheckBox.isChecked()){
            totalPrice.setText(counter*regularprice+" SAR");
        }
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
        if (eventOrder.getNational() == null) {
            nationalIdTextView.setVisibility(View.GONE);
        }else {
            nationalIdTextView.setText(eventOrder.getNational());
        }
        paymentMethod.setText(eventOrder.getPaymentMethod());
        vipPrice.setText(eventOrder.getVipPrice());
        vipprice = Integer.parseInt(eventOrder.getVipPrice());
        regularprice = Integer.parseInt(eventOrder.getRegularPrice());
        regularPrice.setText(eventOrder.getRegularPrice());
        numTickets.setText("Only "+eventOrder.getTickets()+" tickets are available");
        totalPrice.setText("");
        vipCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    regularCheckBox.setChecked(false);
                }
            }
        });
        regularCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    vipCheckBox.setChecked(false);
                }
            }
        });
        EventDaysAdapter adapter = new EventDaysAdapter(this.getContext());
        adapter.setDays(eventOrder.getEventDateDays());
        daysGridView.setAdapter(adapter);
        daysGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout layout = view.findViewById(R.id.eventDayContainer);
                layout.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.rounded_rectangle_black));
            }
        });
    }
}
