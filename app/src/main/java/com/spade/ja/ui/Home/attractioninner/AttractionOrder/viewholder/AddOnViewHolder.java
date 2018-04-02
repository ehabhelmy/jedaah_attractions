package com.spade.ja.ui.Home.attractioninner.AttractionOrder.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.viewtickets.Addon;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

import butterknife.BindView;

/**
 * Created by ehab on 3/17/18.
 */

public class AddOnViewHolder extends BaseViewHolder<Addon> {

    @BindView(R.id.ticketType)
    TextView ticketType;

    @BindView(R.id.price)
    TextView ticketPrice;

    @BindView(R.id.ticketDescription)
    TextView ticketDescription;

    @BindView(R.id.imagePlus)
    ImageView plus;

    @BindView(R.id.imageMinus)
    ImageView minus;

    @BindView(R.id.quantity)
    TextView quantity;

    private int ticketNumber;
    private int ticketsPrice;
    private int id;
    int count = 0;

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getTicketsPrice() {
        return ticketsPrice;
    }

    public int getId() {
        return id;
    }

    public AddOnViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Addon baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        ticketType.setText(baseModel.getName());
        ticketPrice.setText(baseModel.getPrice());
        ticketDescription.setText(baseModel.getDescription());
        quantity.setText(count+"");
        id = baseModel.getId();
        plus.setOnClickListener(view -> {
            quantity.setText(++count+"");
            ticketNumber = count;
            ticketsPrice = (int) (count * Double.parseDouble(baseModel.getPrice()));
            onFavouriteListener.onFavouriteClicked(Integer.parseInt(baseModel.getPrice()));
            onViewListener.onViewClicked(1);
        });
        minus.setOnClickListener(view -> {
            if (count>1) {
                quantity.setText(--count + "");
                ticketNumber = count;
                ticketsPrice = (int) (count * Double.parseDouble(baseModel.getPrice()));
                onFavouriteListener.onFavouriteClicked(-Integer.parseInt(baseModel.getPrice()));
                onViewListener.onViewClicked(-1);
            }
        });
    }

    @Override
    public void bind(Addon baseModel, int position, TicketListener ticketListener) {
        throw new UnsupportedOperationException();
    }
}
