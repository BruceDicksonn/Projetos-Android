package com.example.berserk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page228 extends AppCompatActivity {

    Button avancar, voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page228);

        avancar = findViewById(R.id.btnAvancar);
        voltar = findViewById(R.id.btnVoltar);

        Intent nextPage = new Intent(getApplicationContext(), Page229.class);
        Intent backPage = new Intent(getApplicationContext(), Page227.class);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(backPage);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(nextPage);
            }
        });

    }
}