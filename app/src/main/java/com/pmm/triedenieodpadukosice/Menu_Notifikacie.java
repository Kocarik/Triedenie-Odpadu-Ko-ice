package com.pmm.triedenieodpadukosice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Bubo on 22. 3. 2016.
 */
public class Menu_Notifikacie extends AppCompatActivity {
    Spinner spinnerUlice;
    Spinner spinnerDobaNotifikacie;

    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterNotifikacie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_notifikacie);

        spinnerUlice=(Spinner)findViewById(R.id.spinnerUlice);
        adapter=ArrayAdapter.createFromResource(this,R.array.UliceKE,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUlice.setAdapter(adapter);

        spinnerUlice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position !=0){
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " vybrané", Toast.LENGTH_SHORT).show();
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
                if(position !=0) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " vybrané", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

