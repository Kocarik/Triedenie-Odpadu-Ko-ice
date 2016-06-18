package com.pmm.triedenieodpadukosice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Client on 28.4.2016.
 */
public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        int dni=intent.getIntExtra("paramdni", 1);

        String textNotifikacie;
        if (dni==1){
            textNotifikacie="O "+dni+" deň sa bude odvážať "+intent.getStringExtra("paramodpad");
        }
        else if (dni>1 && dni<5){
            textNotifikacie="O "+dni+" dni sa bude odvážať "+intent.getStringExtra("paramodpad");
        }
        else{
            textNotifikacie="O "+dni+" dní sa bude odvážať "+intent.getStringExtra("paramodpad");
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
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int mNotificationId = 1;
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int storedDoba = prefs.getInt("doba", 1);
        String storedUlica = prefs.getString("ulica", null);
        System.out.println(storedUlica+" "+storedDoba);
        //new Alarm().setAlarm(context, storedDoba, storedUlica);

    }
}
