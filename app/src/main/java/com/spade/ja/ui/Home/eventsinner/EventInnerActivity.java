package com.spade.ja.ui.Home.eventsinner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.request.changeorder.ChangeOrderRequest;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.spade.ja.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;
import com.telr.mobile.sdk.activty.WebviewActivity;
import com.telr.mobile.sdk.entity.response.status.StatusResponse;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ehab on 12/22/17.
 */

public class EventInnerActivity extends BaseActivity implements EventInnerActivityContract.View {

    @Inject
    EventInnerActivityPresenter presenter;

    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @BindView(R.id.loading_overlay_container)
    LinearLayout loadingView;

    @BindView(R.id.frame_layout_inner)
    FrameLayout container;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Object object = intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);
        if (object instanceof StatusResponse) {
            StatusResponse status = (StatusResponse) object;
            if (status.getAuth() != null) {
                status.getAuth().getAvs();      /* Result of the AVS check:
                                            Y = AVS matched OK
                                            P = Partial match (for example, post-code only)
                                            N = AVS not matched
                                            X = AVS not checked
                                            E = Error, unable to check AVS */
                status.getAuth().getCode();     // If the transaction was authorised, this contains the authorisation code from the card issuer. Otherwise it contains a code indicating why the transaction could not be processed.
                status.getAuth().getMessage();  // The authorisation or processing error message.
                status.getAuth().getCa_valid();
                status.getAuth().getCardcode(); // Code to indicate the card type used in the transaction. See the code list at the end of the document for a list of card codes.
                status.getAuth().getCardlast4();// The last 4 digits of the card number used in the transaction. This is supplied for all payment types (including the Hosted Payment Page method) except for PayPal.
                status.getAuth().getCvv();      /* Result of the CVV check:
                                           Y = CVV matched OK
                                           N = CVV not matched
                                           X = CVV not checked
                                           E = Error, unable to check CVV */
                status.getAuth().getTranref(); //The payment gateway transaction reference allocated to this request.
                Log.d("hany", status.getAuth().getTranref());
                status.getAuth().getCardfirst6(); // The first 6 digits of the card number used in the transaction, only for version 2 is submitted in Tran -> Version
                status.getAuth().getStatus();   // Authorisation status. A indicates an authorised transaction. H also indicates an authorised transaction, but where the transaction has been placed on hold. Any other value indicates that the request could not be processed.
                setTransactionDetails(status.getAuth().getTranref(), status.getAuth().getCardlast4());
            }
        }else {
            String errorMessage = (String) object;
            presenter.changeCreditCardStatus(orderId, ChangeOrderRequest.REJECTED);
            new AlertDialog.Builder(this)
                    .setMessage(errorMessage)
                    .setTitle("Failure")
                    .setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                        finish();
                    })
                    .show();
        }
    }

    private void setTransactionDetails(String ref, String last4) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("telr", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ref", ref);
        editor.putString("last4", last4);
        editor.apply();
        presenter.changeCreditCardStatus(orderId, ChangeOrderRequest.APPROVED);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inner_event;
    }

    @Override
    public void onBackPressed() {
        if (presenter.getCurrentFragment() instanceof EventOrderSuccessFragment) {
            finish();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void showError(String message) {
        showPopUp(message);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }
}
