package com.pmm.triedenieodpadukosice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonNotifikacie;
    private Button buttonInformacie;
    private Button buttonMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotifikacie=(Button) findViewById(R.id.btnNotifikacie);
        buttonInformacie=(Button) findViewById(R.id.btnInformacie);
        buttonMapa=(Button) findViewById(R.id.btnMapa);



        buttonNotifikacie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Menu_Notifikacie.class);
                startActivity(intent);
            }
        });

        buttonInformacie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Menu_Informacie.class);
                startActivity(intent);
            }
        });
    }

}

