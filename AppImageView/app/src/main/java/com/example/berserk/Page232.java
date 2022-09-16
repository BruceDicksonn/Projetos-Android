package com.example.berserk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page232 extends AppCompatActivity {

    Button avancar, voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page232);

        avancar = findViewById(R.id.btnAvancar);
        voltar = findViewById(R.id.btnVoltar);

        Intent nextPage = new Intent(getApplicationContext(), FinalPage.class);
        Intent backPage = new Intent(getApplicationContext(), Page231.class);

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