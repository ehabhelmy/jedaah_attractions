package com.spade.ja.ui.Home.profile.my_tickets.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.spade.ja.ui.widget.ImageLayout;

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
        //Picasso.with(eventImage.getContext()).load(baseModel.getEventImage()).placeholder(R.mipmap.myimage).error(R.mipmap.myimage);
        Glide.with(eventImage.getContext()).load(baseModel.getEventImage()).apply(new RequestOptions().placeholder(R.mipmap.myimage).error(R.mipmap.myimage)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                eventImage.setBackground(resource);
            }
        });
        paymentMethod.setText(baseModel.getPaymentMethod());
        ticketType.setText(baseModel.getTicketType());
        orderNumber.setText(baseModel.getOrderNumber());
        orderStatus.setText(baseModel.getOrderStatus());
        numberTickets.setText(baseModel.getNumberTickets());
        ticketsPrice.setText(baseModel.getTicketsPrice());
    }
}
