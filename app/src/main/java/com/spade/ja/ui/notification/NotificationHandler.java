package com.spade.ja.ui.notification;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.spade.ja.ui.navigation.JaNavigationManager;

import org.json.JSONObject;

/**
 * Created by ehab on 3/31/18.
 */

public class NotificationHandler implements OneSignal.NotificationOpenedHandler {

    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String action = null;
        String id = null;

        if (data != null) {
            action = data.optString("type", null);
            id = data.optString("id",null);
        }

        if (actionType == OSNotificationAction.ActionType.Opened) {
            if (action != null && id != null) {
                switch (action) {
                    case "events":
                        JaNavigationManager.getInstance().showEventInnerAsNew(Integer.parseInt(id));
                        break;
                    case "venues":
                        JaNavigationManager.getInstance().showVenueInnerAsNew(Integer.parseInt(id));
                        break;
                    case "attractions" +
                            "":
                        JaNavigationManager.getInstance().showAttractionInnerAsNew(Integer.parseInt(id));
                        break;
                    default:
                        break;
                }
            }
        }
        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
        // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }

}
