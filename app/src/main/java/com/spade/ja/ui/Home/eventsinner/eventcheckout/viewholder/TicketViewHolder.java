package com.spade.ja.ui.Home.eventsinner.eventcheckout.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.EventTicket;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

import butterknife.BindView;

/**
 * Created by ehab on 12/28/17.
 */

public class TicketViewHolder extends BaseViewHolder<EventTicket> {

    @BindView(R.id.vipCheckbox)
    CheckBox ticketCheckBox;

    @BindView(R.id.ticketDescription)
    TextView ticketDescription;

    @BindView(R.id.vipPrice)
    TextView ticketPrice;

    public TicketViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void bind(EventTicket baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
    }

    @Override
    public void bind(EventTicket baseModel, int position, TicketListener ticketListener) {
        ticketCheckBox.setText(baseModel.getType());
        ticketDescription.setText(baseModel.getDescription());
        ticketPrice.setText(baseModel.getPrice());
        ticketCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ticketListener.onTicketChecked(position);
            }
        });
    }
    public void uncheck () {
        ticketCheckBox.setChecked(false);
    }
}
