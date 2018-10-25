package com.spade.ja.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.spade.ja.BuildConfig;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.login.User;
import com.telr.mobile.sdk.activty.WebviewActivity;
import com.telr.mobile.sdk.entity.request.payment.Address;
import com.telr.mobile.sdk.entity.request.payment.App;
import com.telr.mobile.sdk.entity.request.payment.Billing;
import com.telr.mobile.sdk.entity.request.payment.MobileRequest;
import com.telr.mobile.sdk.entity.request.payment.Name;
import com.telr.mobile.sdk.entity.request.payment.Tran;

import java.math.BigInteger;
import java.util.Random;

public class PaymentGateWay {


    public static final String KEY = "jVd2m^fJT9-FFDJ4";
    public static final String STORE_ID = "20783";
    public static final boolean isSecurityEnabled = true;      // Mark false to test on simulator, True to test on actual device and Production

    public static void pay(Context context, User user, String amount, String activityName) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        SharedPreferences sharedPref = context.getSharedPreferences("telr", Context.MODE_PRIVATE);
        String ref = sharedPref.getString("ref", null);
        if (ref == null) {
            intent.putExtra(WebviewActivity.EXTRA_MESSAGE, getMobileRequest(user, amount));
        } else {
            intent.putExtra(WebviewActivity.EXTRA_MESSAGE, getMobileRequestWithContAuth(ref, amount, user));
        }
        intent.putExtra(WebviewActivity.SUCCESS_ACTIVTY_CLASS_NAME, activityName);
        intent.putExtra(WebviewActivity.FAILED_ACTIVTY_CLASS_NAME, activityName);
        intent.putExtra(WebviewActivity.IS_SECURITY_ENABLED, isSecurityEnabled);
        context.startActivity(intent);
    }

    // This example used for the first payment, or with new card details.
    private static MobileRequest getMobileRequest(User user, String amount) {
        MobileRequest mobile = createPaymentRequest(user);
        Tran tran = createTransactionFirstPayment(amount);
        mobile.setTran(tran);
        Billing billing = createBillingInfo(user);
        mobile.setBilling(billing);
        return mobile;

    }

    @NonNull
    private static Tran createTransactionFirstPayment(String amount) {
        Tran tran = new Tran();
        tran.setTest("1");                              // Test mode : Test mode of zero indicates a live transaction. If this is set to any other value the transaction will be treated as a test.
        tran.setType("auth");                           /* Transaction type
                                                            'auth'   : Seek authorisation from the card issuer for the amount specified. If authorised, the funds will be reserved but will not be debited until such time as a corresponding capture command is made. This is sometimes known as pre-authorisation.
                                                            'sale'   : Immediate purchase request. This has the same effect as would be had by performing an auth transaction followed by a capture transaction for the full amount. No additional capture stage is required.
                                                            'verify' : Confirm that the card details given are valid. No funds are reserved or taken from the card.
                                                        */
        tran.setClazz("paypage");                       // Transaction class only 'paypage' is allowed on mobile, which means 'use the hosted payment page to capture and process the card details'
        tran.setCartid(String.valueOf(new BigInteger(128, new Random()))); //// Transaction cart ID : An example use of the cart ID field would be your own transaction or order reference.
        tran.setDescription("Payment");         // Transaction description
        tran.setCurrency("SAR");                        // Transaction currency : Currency must be sent as a 3 character ISO code. A list of currency codes can be found at the end of this document. For voids or refunds, this must match the currency of the original transaction.
        tran.setAmount(amount);                         // Transaction amount : The transaction amount must be sent in major units, for example 9 dollars 50 cents must be sent as 9.50 not 950. There must be no currency symbol, and no thousands separators. Thedecimal part must be separated using a dot.
        tran.setLangauge("en");                        // (Optinal) default is en -> English
        return tran;
    }

    @NonNull
    private static Billing createBillingInfo(User user) {
        Billing billing = new Billing();
        Name name = createName(user);
        billing.setName(name);
        billing.setEmail(user.getEmail());
        billing.setPhone(user.getMobileNumber());                // Phone number, required if enabled in your merchant dashboard.
        return billing;
    }

    @NonNull
    private static Name createName(User user) {
        Name name = new Name();
        String[] names = user.getName().split(" ");
        name.setFirst(names[0]);                          // Forename : the minimum required details for a transaction to be processed
        name.setLast(names.length > 1 ? names[1] : names[0]);                          // Surname : the minimum required details for a transaction to be processed
        name.setTitle("Mr");                           // Title
        return name;
    }

    @NonNull
    private static Address createAddress() {
        Address address = new Address();
        address.setCity("Dubai");                       // City : the minimum required details for a transaction to be processed
        address.setCountry("AE");                       // Country : Country must be sent as a 2 character ISO code. A list of country codes can be found at the end of this document. the minimum required details for a transaction to be processed
        address.setRegion("Dubai");                     // Region
        address.setLine1("SIT G=Towe");                 // Street address – line 1: the minimum required details for a transaction to be processed
        return address;
    }


    /* This example used for continuous authority after using the first request, it used for recurring payment without asking the user to fill again the card details  */
    private static MobileRequest getMobileRequestWithContAuth(String ref, String amount, User user) {
        MobileRequest mobile = createPaymentRequest(user);
        Tran tran = createTransactionResale(ref, amount);
        mobile.setTran(tran);
        return mobile;

    }

    @NonNull
    private static Tran createTransactionResale(String ref, String amount) {
        Tran tran = new Tran();
        tran.setTest("1");                              // Test mode : Test mode of zero indicates a live transaction. If this is set to any other value the transaction will be treated as a test.
        tran.setType("sale");                           /* Transaction type
                                                            'auth'   : Seek authorisation from the card issuer for the amount specified. If authorised, the funds will be reserved but will not be debited until such time as a corresponding capture command is made. This is sometimes known as pre-authorisation.
                                                            'sale'   : Immediate purchase request. This has the same effect as would be had by performing an auth transaction followed by a capture transaction for the full amount. No additional capture stage is required.
                                                            'verify' : Confirm that the card details given are valid. No funds are reserved or taken from the card.
                                                        */
        tran.setClazz("cont");
        tran.setCartid(String.valueOf(new BigInteger(128, new Random()))); //// Transaction cart ID : An example use of the cart ID field would be your own transaction or order reference.
        tran.setDescription("Payment");         // Transaction description
        tran.setCurrency("SAR");                        // Transaction currency : Currency must be sent as a 3 character ISO code. A list of currency codes can be found at the end of this document. For voids or refunds, this must match the currency of the original transaction.
        tran.setAmount(amount);                         // Transaction amount : The transaction amount must be sent in major units, for example 9 dollars 50 cents must be sent as 9.50 not 950. There must be no currency symbol, and no thousands separators. Thedecimal part must be separated using a dot.
        tran.setRef(ref);
        return tran;
    }

    @NonNull
    private static MobileRequest createPaymentRequest(User user) {
        MobileRequest mobile = new MobileRequest();
        mobile.setStore(STORE_ID);                       // Store ID
        mobile.setKey(KEY);                              // Authentication Key : The Authentication Key will be supplied by Telr as part of the Mobile API setup process after you request that this integration type is enabled for your account. This should not be stored permanently within the App.
        App app = setupApp(user);
        mobile.setApp(app);
        return mobile;
    }

    @NonNull
    private static App setupApp(User user) {
        App app = new App();
        app.setId(FirebaseInstanceId.getInstance().getId());                          // Application installation ID
        app.setName("Jeddah Attractions");                    // Application name
        app.setUser(user.getId()+"");                           // Application user ID : Your reference for the customer/user that is running the App. This should relate to their account within your systems.
        app.setVersion(BuildConfig.VERSION_NAME);                         // Application version
        app.setSdk(Build.VERSION.SDK_INT + "");
        return app;
    }
}
