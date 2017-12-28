package com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventTicket;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.TicketDate;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.squareup.picasso.Picasso;

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
        ticketCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ticketListener.onTicketChecked(position);
                }
            }
        });
    }
    public void uncheck () {
        ticketCheckBox.setChecked(false);
    }
}
