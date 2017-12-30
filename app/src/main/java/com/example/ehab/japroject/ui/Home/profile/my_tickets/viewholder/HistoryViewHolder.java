package com.example.ehab.japroject.ui.Home.profile.my_tickets.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.example.ehab.japroject.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.example.ehab.japroject.ui.widget.ImageLayout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by ehab on 12/30/17.
 */

public class HistoryViewHolder extends BaseViewHolder<HistoryEventsUi> {

    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @BindView(R.id.ticketImage)
    ImageLayout eventImage;

    @BindView(R.id.eventMonth)
    TextView eventMonth;

    @BindView(R.id.eventDay)
    TextView eventDay;

    @BindView(R.id.eventTime)
    TextView eventTime;

    @BindView(R.id.paymentMethod)
    TextView paymentMethod;

    @BindView(R.id.ticketType)
    TextView ticketType;

    @BindView(R.id.orderNumber)
    TextView orderNumber;

    @BindView(R.id.orderStatus)
    TextView orderStatus;

    @BindView(R.id.numberTickets)
    TextView numberTickets;

    @BindView(R.id.ticketsPrice)
    TextView ticketsPrice;

    public HistoryViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(HistoryEventsUi baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {

    }

    @Override
    public void bind(HistoryEventsUi baseModel, int position, TicketListener ticketListener) {
        eventTitle.setText(baseModel.getEventTitle());
        eventMonth.setText(baseModel.getEventMonth());
        eventDay.setText(baseModel.getEventDay());
        eventTime.setText(baseModel.getEventTime());
        Picasso.with(eventImage.getContext()).load(baseModel.getEventImage()).placeholder(R.mipmap.myimage).error(R.mipmap.myimage);
        paymentMethod.setText(baseModel.getPaymentMethod());
        ticketType.setText(baseModel.getTicketType());
        orderNumber.setText(baseModel.getOrderNumber());
        orderStatus.setText(baseModel.getOrderStatus());
        numberTickets.setText(baseModel.getNumberTickets());
        ticketsPrice.setText(baseModel.getTicketsPrice());
    }
}
