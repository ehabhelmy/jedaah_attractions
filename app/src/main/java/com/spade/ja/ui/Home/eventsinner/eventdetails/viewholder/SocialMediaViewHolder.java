package com.spade.ja.ui.Home.eventsinner.eventdetails.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;
import com.spade.ja.ui.Base.BaseViewHolder;
import com.spade.ja.ui.Base.listener.RecyclerViewItemListener;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.adapter.TicketListener;

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
        Twitter,Facebook, INSTAGRAM,PHONE
    }

    @Override
    public void bind(SocialMedium baseModel, RecyclerViewItemListener.onViewListener onViewListener, RecyclerViewItemListener.onFavouriteListener onFavouriteListener) {
        socialMediaUrl.setText(baseModel.getUrl());
        socialMediaUrl.setOnClickListener(view -> {
            if (baseModel.getName().equals(JaApplication.getContext().getString(R.string.phone))){
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
//                socialMediaUrl.getContext().startActivity(intent);
            }else {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(baseModel.getUrl()));
                socialMediaUrl.getContext().startActivity(i);
            }
        });
        if (baseModel.getName().equals(SocialMedia.Facebook.name())) {
            Glide.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).apply(new RequestOptions().placeholder(R.drawable.facebook_g).error(R.drawable.facebook_g)).into(socialMediaIcon);
        }else if(baseModel.getName().equals(SocialMedia.INSTAGRAM.name())){
            Glide.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).apply(new RequestOptions().placeholder(R.drawable.instagram_g).error(R.drawable.instagram_g)).into(socialMediaIcon);
        }else if (baseModel.getName().equals(SocialMedia.PHONE.name())){
            Glide.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).apply(new RequestOptions().placeholder(R.drawable.tele).error(R.drawable.tele)).into(socialMediaIcon);
        }else {
            Glide.with(socialMediaIcon.getContext()).load(baseModel.getIcon()).apply(new RequestOptions().placeholder(R.drawable.website_g).error(R.drawable.website_g)).into(socialMediaIcon);
        }
    }

    @Override
    public void bind(SocialMedium baseModel, int position, TicketListener ticketListener) {

    }
}
