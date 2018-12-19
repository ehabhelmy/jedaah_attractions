package com.spade.ja.ui.Home.profile.settings.contactusnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;
import com.spade.ja.ui.Base.BaseRecyclerViewAdapter;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.spade.ja.ui.Home.eventsinner.eventdetails.adapter.SocialMediaAdapter;
import com.spade.ja.ui.Home.eventsinner.eventdetails.viewholder.SocialMediaViewHolder;

public class ContactSocialMediaAdapter extends BaseRecyclerViewAdapter<SocialMedium> {


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_media_contact, parent, false);
        return new SocialMediaViewHolder(view);
    }
}
