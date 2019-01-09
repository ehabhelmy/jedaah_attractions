package com.spade.ja.ui.Home.profile.settings.contactusnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.contact.ContactUsDataResponse;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;
import com.spade.ja.di.DaggerMainComponent;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.eventsinner.eventdetails.adapter.SocialMediaAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactUsNewFragment extends BaseFragment implements ContactUsNewContract.View {

    @Inject
    ContactUsNewPresenter presenter;
    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingOverlayContainer;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.telephone)
    TextView telephone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.website)
    TextView website;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.loginContainer)
    RelativeLayout loginContainer;

    @Override
    protected void initializeDagger() {
        DaggerMainComponent.create().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contactus_new;
    }

    @Override
    protected String getScreenTrackingName() {
        return "contact us";
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getContactData();
    }

    @Override
    public void showLoading() {
        loadingOverlayContainer.setVisibility(View.VISIBLE);
        loginContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingOverlayContainer.setVisibility(View.GONE);
        loginContainer.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.change, R.id.send_mail,R.id.website})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                presenter.call(telephone.getText().toString().trim());
                break;
            case R.id.send_mail:
                presenter.sendMail();
                break;
            case R.id.website:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(website.getText().toString().trim()));
                getActivity().startActivity(i);
                break;
        }
    }

    @Override
    public void setupData(ContactUsDataResponse contactUsResponse) {
        ContactSocialMediaAdapter adapter = new ContactSocialMediaAdapter();
        ContactUsDataResponse.ContactData contactData = contactUsResponse.getData().get(0);
        adapter.setData((ArrayList<SocialMedium>) contactData.getSocialPlatforms());
        list.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        list.setAdapter(adapter);
        address.setText(contactData.getAddress());
        telephone.setText(contactData.getTelephone());
        website.setText(contactData.getWebsite());
        email.setText(contactData.getEmail());
    }
}
