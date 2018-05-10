package com.spade.ja.ui.Home.profile.settings.about;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class AboutFragment extends BaseFragment implements AboutUsContract.View {

    @Inject
    AboutUsPresenter aboutUsPresenter;

    @BindView(R.id.about)
    TextView about;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = aboutUsPresenter;
        aboutUsPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public void setupAboutText(String about) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N ) {
            this.about.setText(Html.fromHtml(about));
        }else {
            this.about.setText(Html.fromHtml(about,Html.FROM_HTML_MODE_COMPACT));
        }
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }
}
