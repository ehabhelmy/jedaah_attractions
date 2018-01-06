package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ehab.japroject.R;
import com.example.ehab.japroject.datalayer.pojo.response.eventinner.SocialMedium;
import com.example.ehab.japroject.ui.Base.BaseViewHolder;
import com.example.ehab.japroject.ui.Base.listener.RecyclerViewItemListener;
import com.example.ehab.japroject.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by ehab on 12/22/17.
 */

public class SocialMediaViewHolder extends BaseViewHolder<SocialMedium> {

    @BindView(R.id.socialMediaIcon)
    ImageView socialMediaIcon;

    @BindView(R.id.SocialMediaurl)
    TextView socialMediaUrl;

    public SocialMediaViewHolder(View itemView) {
        super(itemView);
    }

    enum SocialMedia{
        Twitter,Facebook,Instagram,WebSite;
    }

    @Override
    public void bind(SocialMedium baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        socialMediaUrl.setText(baseModel.getUrl());
        socialMediaUrl.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(baseModel.getUrl()));
            socialMediaUrl.getContext().startActivity(i);
        });
        if (baseModel.getName().equals(SocialMedia.Facebook.name())) {
            Picasso.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).placeholder(R.drawable.ic_facebook_g).error(R.drawable.ic_facebook_g).into(socialMediaIcon);
        }else if (baseModel.getName().equals(SocialMedia.Twitter.name())){
            Picasso.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).placeholder(R.drawable.ic_type_g).error(R.drawable.ic_type_g).into(socialMediaIcon);
        }else if(baseModel.getName().equals(SocialMedia.Instagram.name())){
            Picasso.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).placeholder(R.drawable.ic_instagram_g).error(R.drawable.ic_instagram_g).into(socialMediaIcon);
        }else {
            Picasso.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).placeholder(R.drawable.ic_website_g).error(R.drawable.ic_website_g).into(socialMediaIcon);
        }
    }

    @Override
    public void bind(SocialMedium baseModel, int position, TicketListener ticketListener) {

    }
}
