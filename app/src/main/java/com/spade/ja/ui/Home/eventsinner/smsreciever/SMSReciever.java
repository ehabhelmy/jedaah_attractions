package com.spade.ja.ui.Home.eventsinner.smsreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by ehab on 1/25/18.
 */

public class SMSReciever extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    private SMSListener smsListener;

    public interface SMSListener {
        void onSMSRecieved(String sms);
    }

    public SMSReciever(SMSListener smsListener) {
        this.smsListener = smsListener;
    }

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    smsListener.onSMSRecieved(message);
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
