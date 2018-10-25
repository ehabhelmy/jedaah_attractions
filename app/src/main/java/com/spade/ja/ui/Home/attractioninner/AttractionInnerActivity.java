package com.spade.ja.ui.Home.attractioninner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.request.changeorder.ChangeOrderRequest;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;
import com.telr.mobile.sdk.activty.WebviewActivity;
import com.telr.mobile.sdk.entity.response.status.StatusResponse;

import javax.inject.Inject;

/**
 * Created by ehab on 3/9/18.
 */

public class AttractionInnerActivity extends BaseActivity implements AttractionInnerContract.View {

    @Inject
    AttractionInnerPresenter presenter;

    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attraction_inner;
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Object object = intent.getParcelableExtra(WebviewActivity.PAYMENT_RESPONSE);
        if (object instanceof StatusResponse) {
            StatusResponse status = (StatusResponse) object;
            if (status.getAuth() != null) {
                status.getAuth().getStatus();   // Authorisation status. A indicates an authorised transaction. H also indicates an authorised transaction, but where the transaction has been placed on hold. Any other value indicates that the request could not be processed.
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

                setTransactionDetails(status.getAuth().getTranref(), status.getAuth().getCardlast4());
            }
        }else {
            String errorMessage = (String) object;
            presenter.changeCreditCardStatus(orderId, ChangeOrderRequest.REJECTED);
            showPopUp(errorMessage);
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
}
