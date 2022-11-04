package com.example.traveltootherapp;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button galery,
           telefone,
           configuration,
           gmail,
           maps,
           calendar,
           calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToGalery();
            }
        });

        configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToConfigurations();
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToGmail();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToCalendar();
            }
        });

        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToCall();
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToMaps();
            }
        });

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelToCalculator();
            }
        });

    }

    public void travelToGalery(){
        Intent galery = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,Intent.CATEGORY_APP_GALLERY);

        try {
            startActivity(galery);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void travelToConfigurations(){
        Intent config = new Intent(Settings.ACTION_SETTINGS);

        try {
            startActivity(config);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void travelToGmail(){
        Intent gmail = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,Intent.CATEGORY_APP_EMAIL);

        try {
            startActivity(gmail);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void travelToCalendar(){
        Intent calendar = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,Intent.CATEGORY_APP_CALENDAR);

        try {
            startActivity(calendar);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void travelToMaps(){
        Intent maps = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,Intent.CATEGORY_APP_MAPS);

        try {
            startActivity(maps);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void travelToCalculator(){
        Intent calc = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,Intent.CATEGORY_APP_CALCULATOR);

        try {
            startActivity(calc);
        } catch (ActivityNotFoundException e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setMessage("App não encontrado");
            alert.create();
            alert.show();
        }

    }

    public void travelToCall(){
        Intent call = new Intent(Intent.ACTION_DIAL);

        try {
            startActivity(call);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }

    public void initComponents(){
        galery = findViewById(R.id.galeria);
        configuration = findViewById(R.id.configuracao);
        gmail = findViewById(R.id.gmail);
        calendar = findViewById(R.id.calendar);
        maps = findViewById(R.id.maps);
        calculator = findViewById(R.id.calculator);
        telefone = findViewById(R.id.telefone);
    }

}