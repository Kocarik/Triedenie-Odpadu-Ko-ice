package com.pmm.triedenieodpadukosice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.Calendar;

/**
 * Created by Client on 29.4.2016.
 */
public class Alarm {

    public void setAlarmDateTime(Context context, int year, int month, int day, int hour, int minute, int second, int dni, String odpad){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        /*
        //mesiac od 0
        cal.set(Calendar.MONTH,3);
        cal.set(Calendar.YEAR,2016);
        cal.set(Calendar.DAY_OF_MONTH,29);
        cal.set(Calendar.HOUR_OF_DAY,8);
        cal.set(Calendar.MINUTE,07);
        cal.set(Calendar.SECOND,0);
         */

        AlarmManager alarms = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        context.registerReceiver(receiver, filter);

        Intent intent = new Intent("ALARM_ACTION");
        intent.putExtra("paramdni", dni);
        intent.putExtra("paramodpad", odpad);

        PendingIntent operation = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, operation) ;
        //alarms.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), operation) ;
    }


}
