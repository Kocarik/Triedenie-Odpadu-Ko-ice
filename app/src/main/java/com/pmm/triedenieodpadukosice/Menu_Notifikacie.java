package com.pmm.triedenieodpadukosice;

import android.content.Context;
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

    public final int HODINA=7;
    public final int MINUTA=00;
    public final int SEKUNDA=0;

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
        new Alarm().setAlarm2Notify(this, 2016, 3, 29, 9, 11, 0, 1, "sklo");
        /*java.util.Date datum = new java.util.Date(nextOdvoz.getDatum().getTime());
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(datum);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        new Alarm().setAlarm2Notify(this, year, month, day, HODINA, MINUTA, SEKUNDA, getDobaNotifikacie(), nextOdvoz.getTyp());*/
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

