package com.pmm.triedenieodpadukosice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

/**
 * Created by Client on 28.4.2016.
 */
public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //int dni=Integer.valueOf(intent.getStringExtra("param2"));
        String textNotifikacie;
        /*if (dni==1){
            textNotifikacie="O "+intent.getStringExtra("param2")+" deň sa bude odvážať "+intent.getStringExtra("param");
        }
        else if (dni>1 && dni<5){
            textNotifikacie="O "+intent.getStringExtra("param2")+" dni sa bude odvážať "+intent.getStringExtra("param");
        }
        else{
            textNotifikacie="O "+intent.getStringExtra("param2")+" dní sa bude odvážať "+intent.getStringExtra("param");
        }*/
        Toast.makeText(context, intent.getStringExtra("param"), Toast.LENGTH_SHORT).show();

        /*    textNotifikacie="O "+intent.getStringExtra("param2")+" dni sa bude odvážať "+intent.getStringExtra("param");
        String scrollingText=context.getString(R.string.notif_scroll_text);*/

        /*NotificationManager mNM;
        mNM = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.icon, textNotifikacie, System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, Menu_Informacie.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(context, context.getText(R.string.alarm_service_label), textNotifikacie, contentIntent);

        // Send the notification.
        // We use a layout id because it is a unique number. We use it later to cancel.
        mNM.notify(R.string.alarm_service_label, notification);*/

        /*     int NOTIFICATION = 123;
        // Name of an intent extra we can use to identify if this service was started to create a notification
        String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
        // The system notification manager
        NotificationManager mNM;
        mNM = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

         /*  // This is the 'title' of the notification
            CharSequence title = context.getString(R.string.notif_scroll_text);
            // This is the icon to use on the notification
            int icon = R.drawable.ic_notif;
            // This is the scrolling text of the notification
            CharSequence text = textNotifikacie;
            // What time to show on the notification
            long time = System.currentTimeMillis();

            Notification notification = new Notification(icon, text, time);

            // The PendingIntent to launch our activity if the user selects this notification
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, Menu_Informacie.class), 0);

            // Set the info for the views that show in the notification panel.
            notification.setLatestEventInfo(this, title, text, contentIntent);

            // Clear the notification when it is pressed
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            // Send the notification to the system.
            mNM.notify(NOTIFICATION, notification);*/

        /*    Notification noti = new Notification.Builder(context)
                .setContentTitle(scrollingText)
                .setContentText(textNotifikacie)
                .setSmallIcon(R.drawable.recycle)
                .build();
                //.setLargeIcon(R.drawable.recycle)
                //.build();*/

    }
}
