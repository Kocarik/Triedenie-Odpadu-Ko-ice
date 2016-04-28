package com.pmm.triedenieodpadukosice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Bubo on 22. 3. 2016.
 */
public class Menu_Notifikacie extends AppCompatActivity {
    Spinner spinnerUlice;
    Spinner spinnerDobaNotifikacie;
    Button btnOK;

    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterNotifikacie;

    private String ulica;
    private int dobaNotifikacie;

    public String getUlica() {
        SharedPreferences settings;
        String text;
        settings = getPreferences(Context.MODE_PRIVATE);
        text = settings.getString("ulica", null);
        return text;
    }

    public int getDobaNotifikacie(){
        SharedPreferences settings;
        int value;
        settings = getPreferences(Context.MODE_PRIVATE);
        value = settings.getInt("doba", 1);
        return value;
    }

    public void setAlarm(){
        Calendar cal = Calendar.getInstance();
        //mesiac od 0
        cal.set(Calendar.MONTH,3);
        cal.set(Calendar.YEAR,2016);
        cal.set(Calendar.DAY_OF_MONTH,28);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,8);
        cal.set(Calendar.SECOND,0);

        AlarmManager alarms = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Receiver receiver = new Receiver();
        IntentFilter filter = new IntentFilter("ALARM_ACTION");
        registerReceiver(receiver, filter);

        Intent intent = new Intent("ALARM_ACTION");
        intent.putExtra("paramdni", 1);
        intent.putExtra("paramodpad", "sklo");
  
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, 0);
        // I choose 3s after the launch of my application
        //alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, operation) ;
        alarms.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), operation) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_notifikacie);

        /*database=new Database();
        Odvoz nextOdvoz=database.getNextOdvoz();
        Date datum=nextOdvoz.getDatum();
        int typ=nextOdvoz.getTyp();
        int den=
        String odpad;
        switch(typ){
            case 0: odpad="sklo"; break;
            case 1: odpad="plast + kov"; break;
            case 2: odpad="papier"; break;
            default: odpad="odpad";
        }*/
        int den;

        spinnerUlice=(Spinner)findViewById(R.id.spinnerUlice);
        adapter=ArrayAdapter.createFromResource(this,R.array.UliceKE,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUlice.setAdapter(adapter);

        spinnerUlice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " vybrané", Toast.LENGTH_SHORT).show();
                    ulica = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerDobaNotifikacie=(Spinner)findViewById(R.id.spinnerDobaNotifikacie);
        adapterNotifikacie=ArrayAdapter.createFromResource(this,R.array.DobaNotifikacie,android.R.layout.simple_spinner_item);
        adapterNotifikacie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDobaNotifikacie.setAdapter(adapterNotifikacie);
        spinnerDobaNotifikacie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " vybrané", Toast.LENGTH_SHORT).show();
                    dobaNotifikacie=Integer.valueOf(parent.getItemAtPosition(position).toString());
                }
                else{
                    dobaNotifikacie=1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnOK=(Button) findViewById(R.id.btnMenu2Ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.apply();
                editor.putString("ulica", ulica);
                editor.putInt("doba", dobaNotifikacie);
                editor.apply();
                //System.out.println(getUlica() + " " + getDobaNotifikacie());
                Toast.makeText(getBaseContext(), getString(R.string.settingsSaved), Toast.LENGTH_SHORT).show();
                setAlarm();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.About_Us_id:
                break;
            case R.id.Contact_us_id:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

