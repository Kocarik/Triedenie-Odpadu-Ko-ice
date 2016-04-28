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
        int dni=intent.getIntExtra("param1", 1);

        String textNotifikacie;
        if (dni==1){
            textNotifikacie="O "+dni+" deň sa bude odvážať "+intent.getStringExtra("param2");
        }
        else if (dni>1 && dni<5){
            textNotifikacie="O "+dni+" dni sa bude odvážať "+intent.getStringExtra("param2");
        }
        else{
            textNotifikacie="O "+dni+" dní sa bude odvážať "+intent.getStringExtra("param2");
        }
        String scrollingText=context.getString(R.string.notif_scroll_text);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notif)
                .setAutoCancel(true)
                .setTicker(scrollingText)
                .setContentTitle(scrollingText)
                .setContentText(textNotifikacie);

        Intent resultIntent = new Intent(context, Menu_Informacie.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        int mNotificationId = 001;
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
